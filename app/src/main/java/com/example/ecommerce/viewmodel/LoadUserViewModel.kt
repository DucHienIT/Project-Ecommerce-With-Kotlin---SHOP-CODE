package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerce.model.User
import com.example.ecommerce.repository.LoadUserRepository
import com.example.ecommerce.utils.singleHelper
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class LoadUserViewModel(private val loadUserRepository: LoadUserRepository) : ViewModel() {

    val userInformationData = MutableLiveData<User>()

    init {
        loadUserRepository.loadUser()
            .singleHelper()
            .subscribe(object : DisposableSingleObserver<User>() {
                override fun onSuccess(user: User) {
                    userInformationData.value = user
                }

                override fun onError(e: Throwable) {
                    // Handle the error appropriately
                }
            })
    }
}
