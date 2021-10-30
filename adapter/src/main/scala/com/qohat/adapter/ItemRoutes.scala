package com.qohat.adapter

import cats.Monad
import com.qohat.domain.Items
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import org.http4s.server.Router
import io.estatico.newtype.macros.newtype
import com.qohat.brand._
import eu.timepit.refined.types.string._
import org.http4s.QueryParamDecoder
import eu.timepit.refined.api.Validate
import org.http4s.ParseFailure
import eu.timepit.refined.api.Refined

final case class ItemRoutes[F[_]: Monad](
    item: Items[F]
) extends Http4sDsl[F] {

    private val prefixPath = "/items"

    object BrandQueryParam extends OptionalQueryParamDecoderMatcher[BrandParam]("brand")

    private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {

        case GET -> Root :? BrandQueryParam(brand) =>
        Ok(brand.fold(items.findAll)(b => items.findBy(b.toDomain)))

    }

    val routes: HttpRoutes[F] = Router(
        prefixPath -> httpRoutes
    )
}