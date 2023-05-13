package com.example.ecommerce.repository

import com.example.ecommerce.model.CartItem
import io.reactivex.rxjava3.core.Single

// Interface AddCartRepository mô tả phương thức addCart() để thêm mặt hàng vào giỏ hàng
interface CartRepository {
    fun cart(access_token: String
    ): Single<List<CartItem>>
}