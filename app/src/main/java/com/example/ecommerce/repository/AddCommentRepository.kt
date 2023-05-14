package com.example.ecommerce.repository

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.CartItem
import com.example.ecommerce.model.Comment
import io.reactivex.rxjava3.core.Single

// Interface AddCartRepository mô tả phương thức addCart() để thêm mặt hàng vào giỏ hàng
interface AddCommentRepository {
    fun addComment(
        id:Int,
        access_token: String,
        text:String
    ): Single<Comment>
}
