package com.qohat

import io.estatico.newtype.macros.newtype
import java.util.UUID

package object user {
    @newtype case class UserId(value: UUID)
    @newtype case class UserName(value: String)
    @newtype case class Password(value: String)
    @newtype case class EncryptedPassword(value: String)
    @newtype case class JwtToken(value: String)

    case class User(id: UserId, name: UserName)

    case class UserWithPassword(
        id: UserId,
        name: UserName,
        password: EncryptedPassword
    )
}