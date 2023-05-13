package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteSubCartDataSource(private val apiService: ApiService) : SubCartDataSource {
    override fun subCart(
        id: Int,
        access_token: String
    ): Single<AddCartMessage> = apiService.subCart(id,access_token)
}