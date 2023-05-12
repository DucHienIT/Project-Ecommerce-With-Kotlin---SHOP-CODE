package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.repository.FavoriteListRepository
import com.example.ecommerce.repository.ListOrderRepository
import com.example.ecommerce.repository.datasource.FavoriteListDataSource
import com.example.ecommerce.repository.datasource.ListOrderDataSource
import io.reactivex.rxjava3.core.Single

class ListOrderImpl(private val listOrderDataSource: ListOrderDataSource) :
    ListOrderRepository {
    override fun listOrder(access_token: String): Single<List<Order>> =
        listOrderDataSource.listOrder(access_token)

}