package com.qohat.application

import scala.concurrent.duration._

import cats.Monad
import com.qohat.domain.PaymentClient
import com.qohat.shoppingcart.Cart
import com.qohat.domain.Orders
import com.qohat.user.UserId
import com.qohat.payment.Card
import com.qohat.orders.OrderId
import com.qohat.domain.ShoppingCart
import cats.data.NonEmptyList
import com.qohat.payment.Payment
import com.qohat.errors._
import cats.MonadThrow
import com.qohat.core.errors.EmptyCartError
import com.qohat.payment._
import com.qohat.domain.Retriable
import com.qohat.domain.Retry
import com.qohat.core.errors.PaymentError
import com.qohat.shoppingcart.CartItem
import squants.market.Money
import com.qohat.domain.Background
import com.qohat.shoppingcart.CartTotal
import retry.RetryPolicy
import org.typelevel.log4cats.Logger

import cats.syntax.all._
import com.qohat.core.errors.OrderError

final case class CheckOut[F[_]: MonadThrow: Retry: Background: Logger](
    payments: PaymentClient[F],
    cart: ShoppingCart[F],
    orders: Orders[F],
    policy: RetryPolicy[F]
) {
    private def processPayment(in: Payment): F[PaymentId] =
        Retry[F]
        .retry(policy, Retriable.Payments)(payments.execute(in))
        .adaptError {
            case e =>
            PaymentError(Option(e.getMessage).getOrElse("Unknown"))
        }

    private def createOrder(
        userId: UserId,
        paymentId: PaymentId,
        items: NonEmptyList[CartItem],
        total: Money
    ): F[OrderId] = {
        val action =
        Retry[F]
            .retry(policy, Retriable.Orders)(orders.create(userId, paymentId, items, total))
            .adaptError {
                case e => OrderError(e.getMessage)
            }

        def bgAction(fa: F[OrderId]): F[OrderId] =
        fa.onError {
            case _ =>
            Logger[F].error(
                s"Failed to create order for Payment: ${paymentId.show}. Rescheduling as a background action"
            ) *>
                Background[F].schedule(bgAction(fa), 1.hour)
        }

        bgAction(action)
    }

    private def ensureNonEmpty[A](xs: List[A]): F[NonEmptyList[A]] =
    MonadThrow[F].fromOption(NonEmptyList.fromList(xs), EmptyCartError)

    def process(userId: UserId, card: Card): F[OrderId] =
        cart.get(userId).flatMap {
        case CartTotal(items, total) =>
            for {
                its <- ensureNonEmpty(items)
                pid <- processPayment(Payment(userId, total, card))
                oid <- createOrder(userId, pid, its, total)
                _   <- cart.delete(userId).attempt.void
            } yield oid
        }
        
}