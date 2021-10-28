package com.qohat.adapter

import cats.Monad
import com.qohat.domain.Brands
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import org.http4s.server.Router
import io.circe.Json
import com.qohat.brand._
import com.qohat.adapter.errors.UserNotAuthenticated

final case class BrandRoutes[F[_]: Monad](brands: Brands[F]) extends Http4sDsl[F] {

    import org.http4s.circe.CirceEntityEncoder._ // for Generic EntityEncoder
    import org.http4s.circe.CirceEntityDecoder._ // for Generic EntityDecoder
    // import io.circe.generic.auto._ // for Generic (case class) Encoder
    import Codecs._
    import io.circe.syntax._ // for asJson
    import cats.syntax.applicativeError._ // for recoverWith and handleErrorWith
    import cats.syntax.functor._ // for map
    import cats.syntax.flatMap._ // for flatMap
    
    private val prefixPath = "/brands"

    private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {
        case GET -> Root => 
            brands
            .findAll
            .map(_.asJson)
            .flatMap(Ok(_))
        
        case request @ POST -> Root =>
            request
            .as[Brand]
            .flatMap(brands.create(_))
            .flatMap(Created(_))
            .handleErrorWith {
                case UserNotAuthenticated(_) => Forbidden()
            }
    }

    val routes: HttpRoutes[F] = Router(prefixPath -> httpRoutes)
}