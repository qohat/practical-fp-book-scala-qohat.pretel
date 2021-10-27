package com.qohat

import io.estatico.newtype.macros.newtype
import java.util.UUID

package object category {
    @newtype case class CategoryId(value: UUID)
    @newtype case class CategoryName(value: String)
    case class Category(id: CategoryId, name: CategoryName)

    trait Categorys[F[_]] {
        def findAll: F[List[Category]]
        def create(name: CategoryName): F[CategoryId]
    }
}