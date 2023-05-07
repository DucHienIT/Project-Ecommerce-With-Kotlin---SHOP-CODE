package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Login
import com.example.ecommerce.repository.LoginRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

/**
 * ViewModel cho màn hình đăng nhập
 *
 * @param loginRepository repository để thực hiện các thao tác đăng nhập
 */
class LoginViewModel(
    private val loginRepository: LoginRepository,
) : BaseViewModel() {
    // LiveData để quản lý dữ liệu trả về khi đăng nhập
    val loginLiveData = MutableLiveData<Login>()
    // LiveData để quản lý lỗi khi đăng nhập
    val loginErrorLiveData = MutableLiveData<String?>()
    // LiveData để quản lý trạng thái đăng nhập
    val checkLoginStatus = MutableLiveData<Boolean>()

    /**
     * Thực hiện đăng nhập
     *
     * @param username tên đăng nhập
     * @param password mật khẩu
     */
    fun login(
        username: String,
        password: String
    ) {
        progressbarLiveData.postValue(true)
        loginRepository.login(username, password)
            .doFinally {
                progressbarLiveData.postValue(false)
            }
            .singleHelper()
            .subscribe(object : Observer<Login>(compositeDisposable) {
                override fun onSuccess(t: Login) {
                    loginLiveData.value = t
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    loginErrorLiveData.value = e?.toString()
                }
            })
    }

    /**
     * Kiểm tra trạng thái đăng nhập
     */
    fun checkLogin() {
        checkLoginStatus.value = loginRepository.checkLogin()
    }
}
