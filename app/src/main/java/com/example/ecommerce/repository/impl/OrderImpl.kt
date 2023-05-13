package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Order
import com.example.ecommerce.repository.OrderRepository
import com.example.ecommerce.repository.datasource.OrderDataSource
import io.reactivex.rxjava3.core.Single

class OrderImpl(private val favoriteListDataSource: OrderDataSource) :
    OrderRepository {
    override fun order(access_token: String): Single<Order> =
        favoriteListDataSource.order(access_token)

}