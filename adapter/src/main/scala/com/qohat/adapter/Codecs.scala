package com.qohat.adapter

import io.circe.{Decoder, Encoder, Json}
import java.util.UUID
import com.qohat.brand._


object Codecs {

    private val ID = "id"
    private val NAME = "name"

    implicit val brandDecoder: Decoder[Brand] =
        Decoder.instance[Brand] { cursor =>
            for {
                id <- cursor.downField(ID).as[UUID]
                name <- cursor.downField(NAME).as[String]
            } yield Brand(BrandId(id), BrandName(name))
        }

    implicit val brandEncoder: Encoder[Brand] =
        Encoder.instance[Brand] { b =>
            Json.obj(
                (ID, Json.fromString(b.id.value.toString())),
                (NAME, Json.fromString(b.name.value))
            )
        }
}