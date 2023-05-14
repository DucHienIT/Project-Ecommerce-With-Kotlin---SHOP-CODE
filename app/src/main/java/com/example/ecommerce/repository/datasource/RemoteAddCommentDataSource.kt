package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.CartItem
import com.example.ecommerce.model.Comment
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteAddCommentDataSource(private val apiService: ApiService) : AddCommentDataSource {
    override fun addComment(
        id:Int,
        access_token: String,
        text:String
    ): Single<Comment> =
        apiService.addComment(id,access_token,text)


}
