package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Comment
import com.example.ecommerce.repository.CommentSoftwareRepository
import com.example.ecommerce.repository.datasource.CommentDataSource
import io.reactivex.rxjava3.core.Single

class CommentSoftwareRepositoryImpl(private val commentDataSource:  CommentDataSource) :
    CommentSoftwareRepository {
    override fun commentSoftware(id: Int): Single<List<Comment>> =
        commentDataSource.commentSoftware(id)
}