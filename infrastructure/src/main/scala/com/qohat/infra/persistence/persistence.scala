package com.qohat.infra.persistence

import monocle.Iso
import io.circe.Encoder
import io.estatico.newtype.macros.newtype

package object persistence {

    sealed trait Status
    object Status {
        case object Okay        extends Status
        case object Unreachable extends Status

        val _Bool: Iso[Status, Boolean] =
            Iso[Status, Boolean] {
            case Okay        => true
            case Unreachable => false
            }(if (_) Okay else Unreachable)

        implicit val jsonEncoder: Encoder[Status] =
            Encoder.forProduct1("status")(_.toString)
    }

    //@derive(jsonEncoder)
    @newtype
    case class RedisStatus(value: Status)

    //@derive(jsonEncoder)
    @newtype
    case class PostgresStatus(value: Status)

    //@derive(encoder)
    case class AppStatus(
        redis: RedisStatus,
        postgres: PostgresStatus
    )

    trait HealthCheck[F[_]] {
        def status: F[AppStatus]
    }
}