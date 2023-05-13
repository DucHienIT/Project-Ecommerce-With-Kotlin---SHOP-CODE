package com.example.ecommerce.repository

import com.example.ecommerce.model.AddCartMessage
import io.reactivex.rxjava3.core.Single

// Interface AddCartRepository mô tả phương thức addCart() để thêm mặt hàng vào giỏ hàng
interface SubCartRepository {
    fun subCart(
        id: Int,
        access_token: String
    ): Single<AddCartMessage>
}