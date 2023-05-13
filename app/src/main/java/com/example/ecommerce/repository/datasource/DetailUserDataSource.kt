package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.User
import io.reactivex.rxjava3.core.Single

interface DetailUserDataSource {
    fun detailUser(access_token:String): Single<User>
}