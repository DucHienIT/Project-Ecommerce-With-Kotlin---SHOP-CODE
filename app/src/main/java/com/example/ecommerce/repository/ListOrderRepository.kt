package com.example.ecommerce.repository

import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import io.reactivex.rxjava3.core.Single

interface ListOrderRepository {
    fun listOrder(access_token:String):Single<List<Order>>
}