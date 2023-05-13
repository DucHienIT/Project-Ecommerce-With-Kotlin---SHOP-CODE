package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.OrderDetail
import com.example.ecommerce.model.SoftwareDetail
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteOrderDetailDataSource(private val apiService: ApiService) : OrderDetailDataSource {
    override fun orderDetail(id: Int, access_token: String): Single<OrderDetail> = apiService.orderDetail(id, access_token)


}