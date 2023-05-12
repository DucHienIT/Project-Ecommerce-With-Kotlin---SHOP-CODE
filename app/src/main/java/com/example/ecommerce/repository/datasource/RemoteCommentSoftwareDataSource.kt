package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Comment
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteCommentSoftwareDataSource(private val apiService: ApiService) : CommentDataSource {
    override fun commentSoftware(id: Int): Single<List<Comment>> = apiService.commentSoftware(id)
}