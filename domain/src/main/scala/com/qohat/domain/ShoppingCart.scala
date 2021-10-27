package com.qohat.domain

import com.qohat.user.UserId
import com.qohat.item.ItemId
import com.qohat.shoppingcart.Quantity
import com.qohat.shoppingcart.CartTotal
import com.qohat.shoppingcart.Cart

trait ShoppingCart[F[_]] {
    def add(userId: UserId, itemId: ItemId, quantity: Quantity): F[Unit]
    def get(userId: UserId): F[CartTotal]
    def delete(userId: UserId): F[Unit]
    def removeItem(userId: UserId, itemId: ItemId): F[Unit]
    def update(userId: UserId, cart: Cart): F[Unit]
}