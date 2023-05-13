package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.CartItem
import io.reactivex.rxjava3.core.Single

// Interface AddCartDataSource mô tả các phương thức cần thiết để thực hiện hoạt động thêm mặt hàng vào giỏ hàng
interface CartDataSource {
    fun cart(access_token: String): Single<List<CartItem>>
}