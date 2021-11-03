package com.qohat

import com.qohat.ext.queryParam
import com.qohat.ext.refined._
import io.estatico.newtype.macros.newtype
import java.util.UUID
import eu.timepit.refined.types.string.NonEmptyString
import eu.timepit.refined.auto._
import eu.timepit.refined.cats._
import eu.timepit.refined.types.string.NonEmptyString
import io.circe.refined._
import io.circe.{Decoder, Encoder}

package object brand {
    @newtype case class BrandId(value: UUID = UUID.randomUUID())
    @newtype case class BrandName(value: String)
    case class Brand(id: BrandId, name: BrandName)

    @derive(queryParam, show)
    @newtype
    case class BrandParam(value: NonEmptyString) {
        def toDomain: BrandName = BrandName(value.toString().toLowerCase.capitalize)
    }

    object BrandParam {
        implicit val jsonEncoder: Encoder[BrandParam] =
        Encoder.forProduct1("name")(_.value)

        implicit val jsonDecoder: Decoder[BrandParam] =
        Decoder.forProduct1("name")(BrandParam.apply)
    }
}

