package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Comment
import com.example.ecommerce.model.Order
import com.example.ecommerce.model.Rating
import com.example.ecommerce.model.SoftwareDetail
import com.example.ecommerce.repository.CommentSoftwareRepository
import com.example.ecommerce.repository.DetailSoftwareRepository
import com.example.ecommerce.repository.OrderRepository
import com.example.ecommerce.repository.RatingSoftwareRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

class OrderDetailViewModel(
    orderDetailRepository: OrderRepository,
    val id: Int
) :
    BaseViewModel() {
    // LiveData chứa thông tin chi tiết sản phẩm.
    val orderDetailLiveData = MutableLiveData<Order>()
    // LiveData chứa danh sách đánh giá của sản phẩm.

    private val orderDetailIdLiveData = MutableLiveData<Int>()

    init {
        // Hiển thị tiến trình tải dữ liệu.
        progressbarLiveData.value = true
        // Thiết lập id sản phẩm.
        orderDetailIdLiveData.value = id
        // Lấy thông tin chi tiết sản phẩm từ repository.
        orderDetailRepository.orderDetail(orderDetailIdLiveData.value!!)
            .singleHelper()
            .subscribe(object : Observer<Order>(compositeDisposable) {
                override fun onSuccess(t: Order) {
                    orderDetailLiveData.value = t
                }
            })
        // Lấy danh sách đánh giá của sản phẩm từ repository.
        /*orderDetailRepository.orderDetail(orderDetailIdLiveData.value!!)
            .singleHelper()
            .doFinally {
                // Ẩn tiến trình tải dữ liệu.
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Order>>(compositeDisposable) {
                override fun onSuccess(t: List<Order>) {
                    orderDetailLiveData.value = t
                }
            })
        orderDetailRepository.orderDetail(orderDetailIdLiveData.value!!)
            .singleHelper()
            .doFinally {
                // Ẩn tiến trình tải dữ liệu.
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentSoftwareLiveData.value = t
                }
            })
*/
    }
}
