package com.qohat.domain

import com.qohat.category.Category
import com.qohat.category.CategoryName
import com.qohat.category.CategoryId

trait Categorys[F[_]] {
    def findAll: F[List[Category]]
    def create(name: CategoryName): F[CategoryId]
}