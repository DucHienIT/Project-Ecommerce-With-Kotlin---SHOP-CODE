package com.example.ecommerce.repository

import com.example.ecommerce.model.User
import io.reactivex.rxjava3.core.Single

interface DetailUserRepository {
    fun detailUser(access_token:String):Single<User>
}