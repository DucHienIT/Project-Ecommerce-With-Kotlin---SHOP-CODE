package com.example.ecommerce.repository

import com.example.ecommerce.model.Price
import io.reactivex.rxjava3.core.Single

interface PriceSoftwareRepository {
    fun priceSoftware(id:Int):Single<List<Price>>
}