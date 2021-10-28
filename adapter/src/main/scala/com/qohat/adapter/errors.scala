package com.qohat.adapter

package object errors {
    case class UserNotAuthenticated(message: String)
    case object EmptyCartError
}