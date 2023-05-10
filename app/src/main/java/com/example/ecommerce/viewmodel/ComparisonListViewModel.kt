package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Software
import com.example.ecommerce.repository.ComparisonListRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

class ComparisonListViewModel(
    comparisonListRepository: ComparisonListRepository,
    val id: Int
) : BaseViewModel() {
    val comparisonListSoftwareLiveData = MutableLiveData<List<Software>>()
    private val comparisonListSoftwareIdLiveData = MutableLiveData<Int>()

    /**
     * Lấy thông tin các sản phẩm trong danh sách so sánh với id được truyền vào
     * và gán vào [comparisonListSoftwareLiveData]
     */
    init {
        progressbarLiveData.value = true
        comparisonListSoftwareIdLiveData.value = id
        comparisonListRepository.comparisonSoftware(comparisonListSoftwareIdLiveData.value!!)
            .singleHelper()
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Software>>(compositeDisposable) {
                override fun onSuccess(t: List<Software>) {
                    comparisonListSoftwareLiveData.value = t
                }
            })
    }
}
