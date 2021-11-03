package com.qohat

import java.util.UUID

import com.qohat.brand._
import com.qohat.category._
import com.qohat.optics._

import derevo.cats._
import derevo.circe.magnolia._
import derevo.derive
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.cats._
import eu.timepit.refined.string.{ Uuid, ValidBigDecimal }
import eu.timepit.refined.types.string.NonEmptyString
import io.circe.refined._
import io.estatico.newtype.macros.newtype
import squants.market._


package object item {

    @derive(decoder, encoder, keyDecoder, keyEncoder, eqv, show, uuid)
    @newtype case class ItemId(value: UUID)

    @derive(decoder, encoder, eqv, show)
    @newtype case class ItemName(value: String)

    @derive(decoder, encoder, eqv, show)
    @newtype case class ItemDescription(value: String)

    @derive(decoder, encoder, eqv, show)
    case class Item(
        uuid: ItemId,
        name: ItemName,
        description: ItemDescription,
        price: Money,
        brand: Brand,
        category: Category
    )
}
