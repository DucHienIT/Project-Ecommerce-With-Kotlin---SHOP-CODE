package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Price
import com.example.ecommerce.repository.PriceSoftwareRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

class PriceSoftwareViewModel(
    priceSoftwareRepository: PriceSoftwareRepository,
    val id: Int
) : BaseViewModel() {
    val priceSoftwareLiveData = MutableLiveData<List<Price>>()
    private val priceSoftwareIdLiveData = MutableLiveData<Int>()

    init {
        progressbarLiveData.value = true
        priceSoftwareIdLiveData.value = id
        priceSoftwareRepository.priceSoftware(priceSoftwareIdLiveData.value!!)
            .singleHelper()
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Price>>(compositeDisposable) {
                override fun onSuccess(t: List<Price>) {
                    priceSoftwareLiveData.value = t
                }
            })
    }
}