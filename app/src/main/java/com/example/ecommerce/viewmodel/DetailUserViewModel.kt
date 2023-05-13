package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.model.OrderDetail
import com.example.ecommerce.model.User
import com.example.ecommerce.repository.DetailUserRepository
import com.example.ecommerce.repository.FavoriteListRepository
import com.example.ecommerce.repository.ListOrderRepository
import com.example.ecommerce.repository.OrderDetailRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.utils.singleHelper

class DetailUserViewModel(
    detailUserRepository: DetailUserRepository
) :
    BaseViewModel() {
    // LiveData chứa thông tin chi tiết đơn hàng.
    val detailUserLiveData = MutableLiveData<User>()

    init {

        detailUserRepository.detailUser("Bearer ${TokenHolder.access_token}")
            .singleHelper()
            .subscribe(object : Observer<User>(compositeDisposable) {
                override fun onSuccess(t: User) {
                    detailUserLiveData.value = t
                }
            })

    }
}

