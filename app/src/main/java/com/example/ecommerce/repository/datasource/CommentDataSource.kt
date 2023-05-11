package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Comment
import com.example.ecommerce.model.Property
import io.reactivex.rxjava3.core.Single

interface CommentDataSource {
    fun commentSoftware(id: Int): Single<List<Comment>>
}