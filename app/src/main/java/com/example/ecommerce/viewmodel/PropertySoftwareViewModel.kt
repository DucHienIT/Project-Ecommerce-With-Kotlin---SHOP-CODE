package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Property
import com.example.ecommerce.repository.PropertySoftwareRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

class PropertySoftwareViewModel(
    propertySoftwareRepository: PropertySoftwareRepository,
    val id: Int
) :
    BaseViewModel() {
    val propertySoftwareLiveData = MutableLiveData<List<Property>>()
    private val propertySoftwareIdLiveData = MutableLiveData<Int>()

    init {
        progressbarLiveData.value = true
        propertySoftwareIdLiveData.value = id
        propertySoftwareRepository.propertySoftware(propertySoftwareIdLiveData.value!!)
            .singleHelper()
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<Property>>(compositeDisposable) {
                override fun onSuccess(t: List<Property>) {
                    propertySoftwareLiveData.value = t
                }
            })

    }
}