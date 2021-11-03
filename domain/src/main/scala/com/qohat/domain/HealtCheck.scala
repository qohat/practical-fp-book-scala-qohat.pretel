package com.qohat.domain

import com.qohat.healthcheck.AppStatus

trait HealthCheck[F[_]] {
  def status: F[AppStatus]
}