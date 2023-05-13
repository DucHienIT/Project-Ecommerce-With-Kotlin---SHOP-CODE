package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.model.User
import com.example.ecommerce.repository.DetailUserRepository
import com.example.ecommerce.repository.FavoriteListRepository
import com.example.ecommerce.repository.ListOrderRepository
import com.example.ecommerce.repository.datasource.DetailUserDataSource
import com.example.ecommerce.repository.datasource.FavoriteListDataSource
import com.example.ecommerce.repository.datasource.ListOrderDataSource
import io.reactivex.rxjava3.core.Single

class DetailUserImpl(private val detailUserDataSource: DetailUserDataSource) :
    DetailUserRepository {
    override fun detailUser(access_token: String): Single<User> =
        detailUserDataSource.detailUser(access_token)

}