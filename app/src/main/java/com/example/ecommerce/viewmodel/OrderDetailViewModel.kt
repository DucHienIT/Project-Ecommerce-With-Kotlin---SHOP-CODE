package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Comment
import com.example.ecommerce.model.OrderDetail
import com.example.ecommerce.model.Rating
import com.example.ecommerce.model.SoftwareDetail
import com.example.ecommerce.repository.CommentSoftwareRepository
import com.example.ecommerce.repository.DetailSoftwareRepository
import com.example.ecommerce.repository.OrderDetailRepository
import com.example.ecommerce.repository.RatingSoftwareRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.utils.singleHelper


class OrderDetailViewModel(
    orderDetailRepository: OrderDetailRepository,
    val id: Int
) :
    BaseViewModel() {
    // LiveData chứa thông tin chi tiết đơn hàng.
    val orderDetailLiveData = MutableLiveData<OrderDetail>()

    private val orderDetailIdLiveData = MutableLiveData<Int>()

    init {
        // Hiển thị tiến trình tải dữ liệu.
        progressbarLiveData.value = true
        // Thiết lập id đơn hàng
        orderDetailIdLiveData.value = id

        orderDetailRepository.orderDetail(orderDetailIdLiveData.value!!, "Bearer ${TokenHolder.access_token}")
            .singleHelper()
            .subscribe(object : Observer<OrderDetail>(compositeDisposable) {
                override fun onSuccess(t: OrderDetail) {
                    orderDetailLiveData.value = t
                }
            })

    }
}
