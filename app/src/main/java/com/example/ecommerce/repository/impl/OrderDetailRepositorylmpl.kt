package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.OrderDetail
import com.example.ecommerce.model.SoftwareDetail
import com.example.ecommerce.repository.DetailSoftwareRepository
import com.example.ecommerce.repository.OrderDetailRepository
import com.example.ecommerce.repository.datasource.DetailSoftwareDataSource
import com.example.ecommerce.repository.datasource.OrderDetailDataSource
import io.reactivex.rxjava3.core.Single

class OrderDetailRepositorylmpl(private val orderDetailDataSource: OrderDetailDataSource) :
    OrderDetailRepository {
    override fun orderDetail(id:Int, access_token: String): Single<OrderDetail> =
        orderDetailDataSource.orderDetail(id, access_token)
}