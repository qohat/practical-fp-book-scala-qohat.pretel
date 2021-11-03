package com.qohat.core

import scala.util.control.NoStackTrace

package object errors {
    case class UserNotAuthenticated(message: String) extends NoStackTrace
    case class PaymentError(message: String) extends NoStackTrace
    case class OrderError(message: String) extends NoStackTrace
    case object EmptyCartError extends NoStackTrace
}