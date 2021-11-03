package com.qohat.adapter

import cats.Monad
import com.qohat.domain.HealthCheck
import org.http4s.dsl.Http4sDsl
import org.http4s._
import org.http4s.server.Router
import org.http4s.circe.CirceEntityEncoder._

final case class HealthRoutes[F[_]: Monad](
    healthCheck: HealthCheck[F]
) extends Http4sDsl[F] {

  private val prefixPath = "/healthcheck"

  private val httpRoutes: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case GET -> Root => Ok(healthCheck.status)
    }

  val routes: HttpRoutes[F] = Router(
    prefixPath -> httpRoutes
  )

}