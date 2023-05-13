package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.repository.AddCartRepository
import com.example.ecommerce.repository.SubCartRepository
import com.example.ecommerce.repository.datasource.AddCartDataSource
import com.example.ecommerce.repository.datasource.SubCartDataSource
import io.reactivex.rxjava3.core.Single


// Lớp AddCartImpl triển khai AddCartRepository và sử dụng AddCartDataSource để thực hiện hoạt động thêm mặt hàng vào giỏ hàng
class SubCartImpl(private val subCartDataSource: SubCartDataSource) : SubCartRepository
{
    override fun subCart(
        id: Int,
        access_token: String
    ): Single<AddCartMessage> =
        subCartDataSource.subCart(id,access_token)
}