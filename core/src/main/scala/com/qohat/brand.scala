package com.qohat

import io.estatico.newtype.macros.newtype
import java.util.UUID

package object brand {
    @newtype case class BrandId(value: UUID)
    @newtype case class BrandName(value: String)
    case class Brand(id: BrandId, name: BrandName)
}
