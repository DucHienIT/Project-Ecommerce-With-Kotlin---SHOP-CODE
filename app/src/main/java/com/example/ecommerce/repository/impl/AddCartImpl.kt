package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.repository.AddCartRepository
import com.example.ecommerce.repository.datasource.AddCartDataSource
import io.reactivex.rxjava3.core.Single


// Lớp AddCartImpl triển khai AddCartRepository và sử dụng AddCartDataSource để thực hiện hoạt động thêm mặt hàng vào giỏ hàng
class AddCartImpl(private val addCartDataSource: AddCartDataSource) : AddCartRepository
{
    override fun addCart(
        id:Int,
        access_token: String
    ): Single<AddCartMessage> =
        addCartDataSource.addCart(id,access_token)
}
