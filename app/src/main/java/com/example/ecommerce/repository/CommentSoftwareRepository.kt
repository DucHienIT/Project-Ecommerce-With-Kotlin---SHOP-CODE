package com.example.ecommerce.repository

import com.example.ecommerce.model.Comment
import io.reactivex.rxjava3.core.Single

interface CommentSoftwareRepository {
    fun commentSoftware(id:Int):Single<List<Comment>>
}