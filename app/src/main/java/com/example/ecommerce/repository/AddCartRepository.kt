package com.example.ecommerce.repository

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.CartItem
import io.reactivex.rxjava3.core.Single

// Interface AddCartRepository mô tả phương thức addCart() để thêm mặt hàng vào giỏ hàng
interface AddCartRepository {
    fun addCart(
        id:Int,
        access_token: String
    ): Single<AddCartMessage>
}
