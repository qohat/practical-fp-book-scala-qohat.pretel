package com.qohat

import com.qohat.user.UserId
import com.qohat.item.ItemId
import io.estatico.newtype.macros.newtype
import org.scalactic.anyvals.PosInt
import com.qohat.item.Item
import squants.market.Money


package object shoppingcart {
    @newtype case class Quantity(value: PosInt)
    case class CartTotal(items: List[Item], total: Money)
    case class CartItem(item: Item, quantity: Quantity)
    case class Cart(items: Map[ItemId, Quantity])
}
