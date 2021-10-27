package com.qohat.domain

import com.qohat.payment._

trait PaymentClient[F[_]] {
    def execute(payment: Payment): F[PaymentId]
}