package com.qohat

import io.estatico.newtype.macros.newtype
import java.util.UUID
import com.qohat.payment.PaymentId
import com.qohat.item.ItemId
import com.qohat.shoppingcart.Quantity
import squants.market.Money

package object orders {
    @newtype case class OrderId(value: UUID)
    case class Order(
        id: OrderId,
        pid: PaymentId,
        items: Map[ItemId, Quantity],
        total: Money
    )
}