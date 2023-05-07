package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.ProductDetail
import com.example.ecommerce.model.Rating
import com.example.ecommerce.repository.DetailProductRepository
import com.example.ecommerce.repository.RatingProductRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

/**
 * ViewModel cho màn hình chi tiết sản phẩm, bao gồm thông tin sản phẩm và các đánh giá của sản phẩm.
 * @param detailProductRepository Repository để lấy thông tin chi tiết sản phẩm.
 * @param ratingProductRepository Repository để lấy danh sách các đánh giá của sản phẩm.
 * @param id Id của sản phẩm.
 */
class DetailProductViewModel(
    detailProductRepository: DetailProductRepository,
    ratingProductRepository: RatingProductRepository,
    val id: Int

) :
    BaseViewModel() {
    // LiveData chứa thông tin chi tiết sản phẩm.
    val detailProductLiveData = MutableLiveData<ProductDetail>()
    // LiveData chứa danh sách đánh giá của sản phẩm.
    val ratingProductLiveData = MutableLiveData<List<Rating>>()
    // LiveData chứa id của sản phẩm.
    private val detailProductIdLiveData = MutableLiveData<Int>()

    init {
        // Hiển thị tiến trình tải dữ liệu.
        progressbarLiveData.value = true
        // Thiết lập id sản phẩm.
        detailProductIdLiveData.value = id
        // Lấy thông tin chi tiết sản phẩm từ repository.
        detailProductRepository.detailProduct(detailProductIdLiveData.value!!)
            .singleHelper()
            .subscribe(object : Observer<ProductDetail>(compositeDisposable) {
                override fun onSuccess(t: ProductDetail) {
                    detailProductLiveData.value = t
                }
            })
        // Lấy danh sách đánh giá của sản phẩm từ repository.
        ratingProductRepository.ratingProduct(detailProductIdLiveData.value!!)
            .singleHelper()
            .doFinally {
                // Ẩn tiến trình tải dữ liệu.
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Rating>>(compositeDisposable) {
                override fun onSuccess(t: List<Rating>) {
                    ratingProductLiveData.value = t
                }
            })

    }
}
