package com.example.ecommerce.repository.datasource
import com.example.ecommerce.model.OrderDetail
import com.example.ecommerce.model.Software
import io.reactivex.rxjava3.core.Single

interface OrderDetailDataSource {
    fun orderDetail(id: Int, access_token: String): Single<OrderDetail>
}