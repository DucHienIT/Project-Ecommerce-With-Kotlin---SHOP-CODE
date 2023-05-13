package com.example.ecommerce.repository

import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import io.reactivex.rxjava3.core.Single

interface OrderRepository {
    fun order(access_token:String):Single<Order>
}