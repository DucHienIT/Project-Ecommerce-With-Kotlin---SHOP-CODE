package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.User
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteLoadUserDataSource(private val apiService: ApiService):LoadUserDataSource {
    override fun loadUser(): Single<User> = apiService.getUserInfo()
}