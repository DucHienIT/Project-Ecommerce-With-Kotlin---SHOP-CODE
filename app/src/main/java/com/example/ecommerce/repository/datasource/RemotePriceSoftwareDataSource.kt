package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Price
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemotePriceSoftwareDataSource(private val apiService: ApiService) : PriceSoftwareDataSource {
    override fun priceSoftware(id: Int): Single<List<Price>> = apiService.priceSoftware(id)

}