package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddFavorite
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteOrderDataSource(val apiService: ApiService) : OrderDataSource {
    override fun order(access_token: String): Single<Order> =
        apiService.order(access_token)
}