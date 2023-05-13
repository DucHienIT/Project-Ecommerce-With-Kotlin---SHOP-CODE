package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.CartItem
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteAddCartDataSource(private val apiService: ApiService) : AddCartDataSource {
    override fun addCart(
        id:Int,
        access_token: String
    ): Single<AddCartMessage> =
        apiService.addCart(id,access_token)


}
