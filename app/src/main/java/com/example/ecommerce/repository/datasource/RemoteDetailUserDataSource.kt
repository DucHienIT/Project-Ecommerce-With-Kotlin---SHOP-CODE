package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddFavorite
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.model.User
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteDetailUserDataSource(val apiService: ApiService) : DetailUserDataSource {
    override fun detailUser(access_token: String): Single<User> =
        apiService.detailUser(access_token)

}