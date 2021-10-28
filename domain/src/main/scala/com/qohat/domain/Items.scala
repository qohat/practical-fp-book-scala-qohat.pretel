package com.qohat.domain

import com.qohat.item.Item
import com.qohat.brand.BrandName
import com.qohat.item.ItemId

trait Items[F[_]] {
    def findAll: F[List[Item]]
    def findBy(bandName: BrandName): F[List[Item]]
    def findById(id: ItemId): F[Option[Item]]
    def create(item: Item): F[ItemId]
    def update(id: ItemId, item: Item): F[Unit]
}