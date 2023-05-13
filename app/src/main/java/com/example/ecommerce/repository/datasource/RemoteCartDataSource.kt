package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.CartItem
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteCartDataSource(private val apiService: ApiService) :
    CartDataSource {
    override fun cart( access_token: String): Single<List<CartItem>> = apiService.cart(access_token)
}