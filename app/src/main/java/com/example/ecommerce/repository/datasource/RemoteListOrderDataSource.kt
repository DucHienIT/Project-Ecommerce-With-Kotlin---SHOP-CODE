package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddFavorite
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteListOrderDataSource(val apiService: ApiService) : ListOrderDataSource {
    override fun listOrder(access_token: String): Single<List<Order>> =
        apiService.listOrder(access_token)

}