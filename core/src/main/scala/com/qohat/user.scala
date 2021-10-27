package com.qohat

import io.estatico.newtype.macros.newtype
import java.util.UUID

package object user {
    @newtype case class UserId(id: UUID)
}