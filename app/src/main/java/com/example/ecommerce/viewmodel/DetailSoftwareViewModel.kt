package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Rating
import com.example.ecommerce.model.SoftwareDetail
import com.example.ecommerce.repository.DetailSoftwareRepository
import com.example.ecommerce.repository.RatingSoftwareRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

/**
 * ViewModel cho màn hình chi tiết sản phẩm, bao gồm thông tin sản phẩm và các đánh giá của sản phẩm.
 * @param detailSoftwareRepository Repository để lấy thông tin chi tiết sản phẩm.
 * @param ratingSoftwareRepository Repository để lấy danh sách các đánh giá của sản phẩm.
 * @param id Id của sản phẩm.
 */
class DetailSoftwareViewModel(
    detailSoftwareRepository: DetailSoftwareRepository,
    ratingSoftwareRepository: RatingSoftwareRepository,
    val id: Int

) :
    BaseViewModel() {
    // LiveData chứa thông tin chi tiết sản phẩm.
    val detailSoftwareLiveData = MutableLiveData<SoftwareDetail>()
    // LiveData chứa danh sách đánh giá của sản phẩm.
    val ratingSoftwareLiveData = MutableLiveData<List<Rating>>()
    // LiveData chứa id của sản phẩm.
    private val detailSoftwareIdLiveData = MutableLiveData<Int>()

    init {
        // Hiển thị tiến trình tải dữ liệu.
        progressbarLiveData.value = true
        // Thiết lập id sản phẩm.
        detailSoftwareIdLiveData.value = id
        // Lấy thông tin chi tiết sản phẩm từ repository.
        detailSoftwareRepository.detailSoftware(detailSoftwareIdLiveData.value!!)
            .singleHelper()
            .subscribe(object : Observer<SoftwareDetail>(compositeDisposable) {
                override fun onSuccess(t: SoftwareDetail) {
                    detailSoftwareLiveData.value = t
                }
            })
        // Lấy danh sách đánh giá của sản phẩm từ repository.
        ratingSoftwareRepository.ratingSoftware(detailSoftwareIdLiveData.value!!)
            .singleHelper()
            .doFinally {
                // Ẩn tiến trình tải dữ liệu.
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Rating>>(compositeDisposable) {
                override fun onSuccess(t: List<Rating>) {
                    ratingSoftwareLiveData.value = t
                }
            })

    }
}
