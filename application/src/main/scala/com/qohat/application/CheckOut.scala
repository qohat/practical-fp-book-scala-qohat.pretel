package com.qohat.application

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
import com.qohat.adapter.errors.EmptyCartError

final case class CheckOut[F[_]: Monad](
    payments: PaymentClient[F],
    cart: ShoppingCart[F],
    orders: Orders[F]
) {
    private def enseruNonEmpty[A](xs: List[A]): F[NonEmptyList[A]] =
        MonadThrow[F].fromOption(
            NonEmptyList.fromList(xs),
            EmptyCartError
        )

    def execute(userId: UserId, card: Card): F[OrderId] =
        for {
            c <- cart.get(userId)
            items <- enseruNonEmpty(c.items)
            pid <- payments.process(Payment(userId, c.total, card))
            oid <- orders.create(userId, pid, items, c.total)
            _ <- cart.delete(userId)
        } yield oid
}