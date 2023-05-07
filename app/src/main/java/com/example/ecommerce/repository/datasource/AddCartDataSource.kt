package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddCart
import io.reactivex.rxjava3.core.Single

// Interface AddCartDataSource mô tả các phương thức cần thiết để thực hiện hoạt động thêm mặt hàng vào giỏ hàng
interface AddCartDataSource {
    fun addCart(
        access_token: String,
        id: Int,
        count: Int,
        price: Int
    ): Single<List<AddCart>>
}