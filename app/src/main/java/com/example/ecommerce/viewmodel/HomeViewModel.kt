package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Category
import com.example.ecommerce.model.Slider
import com.example.ecommerce.model.Software
import com.example.ecommerce.repository.AmazingRepository
import com.example.ecommerce.repository.CategoryRepository
import com.example.ecommerce.repository.SliderRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

class HomeViewModel(
    sliderRepository: SliderRepository, // Repository cho Slider
    categoryRepository: CategoryRepository, // Repository cho Category
    amazingRepository: AmazingRepository // Repository cho sản phẩm ấn tượng
) :
    BaseViewModel() {
    // LiveData để lắng nghe danh sách Slider được trả về từ API
    val sliderLiveData = MutableLiveData<List<Slider>?>()

    // LiveData để lắng nghe danh sách Category được trả về từ API
    val categoryLiveDate = MutableLiveData<List<Category>?>()

    // LiveData để lắng nghe danh sách sản phẩm ấn tượng được trả về từ API
    val amazingLiveDate = MutableLiveData<List<Software>?>()

    init {
        progressbarLiveData.value = true // Hiển thị ProgressBar
        sliderRepository.sliders() // Lấy danh sách Slider
            .singleHelper() // Lấy phần tử duy nhất trong danh sách trả về
            .subscribe(object : Observer<List<Slider>>(compositeDisposable) {
                // Khi lấy được danh sách Slider, cập nhật giá trị cho sliderLiveData
                override fun onSuccess(t: List<Slider>?) {
                    sliderLiveData.value = t
                }
            })
        categoryRepository.category() // Lấy danh sách Category
            .singleHelper() // Lấy phần tử duy nhất trong danh sách trả về
            .subscribe(object : Observer<List<Category>>(compositeDisposable) {
                // Khi lấy được danh sách Category, cập nhật giá trị cho categoryLiveDate
                override fun onSuccess(t: List<Category>?) {
                    categoryLiveDate.value = t
                }
            })
        amazingRepository.amazing() // Lấy danh sách sản phẩm ấn tượng
            .singleHelper() // Lấy phần tử duy nhất trong danh sách trả về
            .doFinally {
                progressbarLiveData.value = false // Ẩn ProgressBar khi đã lấy được danh sách sản phẩm ấn tượng
            }
            .subscribe(object : Observer<List<Software>>(compositeDisposable) {
                // Khi lấy được danh sách sản phẩm ấn tượng, cập nhật giá trị cho amazingLiveDate
                override fun onSuccess(t: List<Software>?) {
                    amazingLiveDate.value = t
                }
            })

    }
}
