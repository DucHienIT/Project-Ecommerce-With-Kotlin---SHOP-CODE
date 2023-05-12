package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.User
import io.reactivex.rxjava3.core.Single

interface LoadUserDataSource {
    fun loadUser(): Single<User>
}