package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.CartItem
import com.example.ecommerce.repository.CartRepository
import com.example.ecommerce.repository.datasource.CartDataSource
import io.reactivex.rxjava3.core.Single


// Lớp AddCartImpl triển khai AddCartRepository và sử dụng AddCartDataSource để thực hiện hoạt động thêm mặt hàng vào giỏ hàng
class CartImpl(private val CartDataSource: CartDataSource) :
    CartRepository
{
    override fun cart(access_token: String): Single<List<CartItem>> = CartDataSource.cart(access_token)
}