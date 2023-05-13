package com.example.ecommerce.repository

import com.example.ecommerce.model.OrderDetail
import com.example.ecommerce.model.SoftwareDetail
import io.reactivex.rxjava3.core.Single

interface OrderDetailRepository {
    fun orderDetail(id:Int, access_token: String): Single<OrderDetail>
}