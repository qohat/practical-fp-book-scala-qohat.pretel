package com.qohat.domain

import com.qohat.user._

trait Users[F[_]] {
  def find(
    username: UserName
  ): F[Option[UserWithPassword]]

  def create(
    username: UserName,
    password: EncryptedPassword
  ): F[UserId]
}