package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.Comment
import com.example.ecommerce.repository.AddCartRepository
import com.example.ecommerce.repository.AddCommentRepository
import com.example.ecommerce.repository.datasource.AddCartDataSource
import com.example.ecommerce.repository.datasource.AddCommentDataSource
import io.reactivex.rxjava3.core.Single


// Lớp AddCartImpl triển khai AddCartRepository và sử dụng AddCartDataSource để thực hiện hoạt động thêm mặt hàng vào giỏ hàng
class AddCommentImpl(private val addCommentDataSource: AddCommentDataSource) : AddCommentRepository
{
    override fun addComment(
        id:Int,
        access_token: String,
        text:String
    ): Single<Comment> =
        addCommentDataSource.addComment(id,access_token,text)
}
