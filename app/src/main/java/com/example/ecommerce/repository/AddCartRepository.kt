package com.example.ecommerce.repository

import com.example.ecommerce.model.CartItem
import io.reactivex.rxjava3.core.Single

// Interface AddCartRepository mô tả phương thức addCart() để thêm mặt hàng vào giỏ hàng
interface AddCartRepository {
    fun addCart(
        access_token: String,
        id: Int,
        count: Int,
        price: Int
    ): Single<List<CartItem>>
}
