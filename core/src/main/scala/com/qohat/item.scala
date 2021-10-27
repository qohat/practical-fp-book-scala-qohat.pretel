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
}
