package com.qohat.domain

import com.qohat.brand.BrandName
import com.qohat.brand.BrandId
import com.qohat.brand.Brand

trait Brands[F[_]] {
    def findAll: F[List[Brand]]
    def create(name: BrandName): F[BrandId]
}