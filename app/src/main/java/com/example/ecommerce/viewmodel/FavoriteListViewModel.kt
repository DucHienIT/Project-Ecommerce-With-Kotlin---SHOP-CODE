package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.repository.FavoriteListRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

class FavoriteListViewModel(
    private val favoriteListRepository: FavoriteListRepository
) : BaseViewModel() {
    val favoriteListLiveData = MutableLiveData<List<FavoriteList>>()

    /**
     * Lấy danh sách sản phẩm yêu thích từ repository
     * @param access_token chuỗi token xác thực người dùng
     */
    fun listFavorite(access_token: String) {
        // Hiển thị progress bar
        progressbarLiveData.value = true

        // Gọi repository để lấy danh sách sản phẩm yêu thích
        favoriteListRepository.listFavorite(access_token)
            .singleHelper()
            .doFinally {
                // Ẩn progress bar
                progressbarLiveData.value = false
            }
            .subscribe(object : Observer<List<FavoriteList>>(compositeDisposable) {
                override fun onSuccess(t: List<FavoriteList>?) {
                    // Cập nhật dữ liệu vào LiveData để cung cấp cho view
                    favoriteListLiveData.value = t!!
                }

            })
    }
}
