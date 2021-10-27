package com.qohat.domain

import com.qohat.user._

trait Auth[F[_]] {
    def findUser(jwtToken: JwtToken): F[Option[User]]
    def newUser(userName: UserName, password: Password): F[JwtToken]
    def login(userName: UserName, password: Password): F[JwtToken]
    def logout(jwtToken: JwtToken, userName: UserName): F[Unit]
}