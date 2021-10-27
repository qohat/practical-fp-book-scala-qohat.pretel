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
    case class Cart(items: Map[ItemId, Quantity])

    trait ShoppingCart[F[_]] {
        def add(userId: UserId, itemId: ItemId, quantity: Quantity): F[Unit]
        def get(userId: UserId): F[CartTotal]
        def delete(userId: UserId): F[Unit]
        def removeItem(userId: UserId, itemId: ItemId): F[Unit]
        def update(userId: UserId, cart: Cart): F[Unit]
    }
}
