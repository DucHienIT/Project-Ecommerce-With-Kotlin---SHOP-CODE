package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.repository.FavoriteListRepository
import com.example.ecommerce.repository.ListOrderRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

class ListOrderViewModel(
    private val listOrderRepository: ListOrderRepository
) : BaseViewModel() {
    val listOrderLiveData = MutableLiveData<List<Order>>()

    /**
     * Lấy danh sách sản phẩm yêu thích từ repository
     * @param access_token chuỗi token xác thực người dùng
     */
    fun listOrder(access_token: String) {
        // Hiển thị progress bar
        progressbarLiveData.value = true

        // Gọi repository để lấy danh sách sản phẩm yêu thích
        listOrderRepository.listOrder(access_token)
            .singleHelper()
            .doFinally {
                // Ẩn progress bar
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Order>>(compositeDisposable) {
                override fun onSuccess(t: List<Order>?) {
                    // Cập nhật dữ liệu vào LiveData để cung cấp cho view
                    listOrderLiveData.value = t!!
                }

            })
    }
}
