package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.CartItem
import com.example.ecommerce.model.Comment
import io.reactivex.rxjava3.core.Single

// Interface AddCartDataSource mô tả các phương thức cần thiết để thực hiện hoạt động thêm mặt hàng vào giỏ hàng
interface AddCommentDataSource {
    fun addCart(
        id:Int,
        access_token: String,
        text:String

    ): Single<Comment>
}
