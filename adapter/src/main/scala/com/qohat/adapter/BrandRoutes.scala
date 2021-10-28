package com.qohat.infra.http

import cats.Monad
import com.qohat.domain.Brands
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import org.http4s.server.Router
import io.circe.Decoder
import java.util.UUID
import io.circe.Encoder
import io.circe.Json

final case class BrandRoutes[F[_]: Monad](brands: Brands[F]) extends Http4sDsl[F] {

    import org.http4s.circe.CirceEntityEncoder._ // for Generic EntityEncoder
    import org.http4s.circe.CirceEntityDecoder._ // for Generic EntityDecoder
    // import io.circe.generic.auto._ // for Generic (case class) Encoder
    import com.qohat.adapter.Codecs._ // for Custom Stream EntityEncoder, Custom Encoder and Custom Decoder
    import io.circe.syntax._ // for asJson
    
    private val prefixPath = "/brands"

    private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {
        case GET -> Root => 
            brands
            .findAll
            .map(_.asJson)
            .flatMap(Ok(_))
            .handleErrorWith {
                case UserNotAuthenticated(_) => Forbidden()
            }
    }

    val routes: HttpRoutes[F] = Router( prefixPath -> httpRoutes)
}