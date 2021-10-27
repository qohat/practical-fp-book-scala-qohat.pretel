package com.qohat.domain

import com.qohat.orders.OrderId
import com.qohat.user.UserId
import com.qohat.orders.Order
import squants.market.Money
import com.qohat.payment.PaymentId
import cats.data.NonEmptyList
import com.qohat.shoppingcart.CartItem

trait Orders[F[_]] {
    def get(
        userId: UserId,
        orderId: OrderId
    ): F[Option[Order]]

    def findBy(userId: UserId): F[List[Order]]

    def create(
        userId: UserId,
        paymentId: PaymentId,
        items: NonEmptyList[CartItem],
        total: Money
    ): F[OrderId]
}