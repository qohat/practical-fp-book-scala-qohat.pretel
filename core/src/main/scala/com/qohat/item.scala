package com.qohat

import java.util.UUID
import io.estatico.newtype.macros.newtype
import com.qohat.brand.BrandName
import com.qohat.brand.Brand
import com.qohat.category.Category
import squants.market.Money

package object item {
    @newtype case class ItemId(value: UUID)
    @newtype case class ItemName(value: String)
    @newtype case class ItemDescription(value: String)

    case class Item(
        uuid: ItemId,
        name: ItemName,
        description: ItemDescription,
        price: Money,
        brand: Brand,
        category: Category
    )

    trait Items[F[_]] {
        def findAll: F[List[Item]]
        def findBy(bandName: BrandName): F[List[Item]]
        def findById(id: ItemId): F[Option[Item]]
        def create(item: Item): F[ItemId]
        def update(id: ItemId, item: Item): F[Unit]
    }
}
