package com.example.ecommerce.repository

import com.example.ecommerce.model.User
import io.reactivex.rxjava3.core.Single

interface LoadUserRepository {
    fun loadUser(): Single<User>
}