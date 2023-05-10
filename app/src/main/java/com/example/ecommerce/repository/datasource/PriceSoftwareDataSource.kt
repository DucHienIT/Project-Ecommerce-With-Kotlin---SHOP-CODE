package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Price
import io.reactivex.rxjava3.core.Single

interface PriceSoftwareDataSource {
    fun priceSoftware(id:Int): Single<List<Price>>
}