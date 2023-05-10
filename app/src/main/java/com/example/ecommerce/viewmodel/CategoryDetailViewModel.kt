package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.Software
import com.example.ecommerce.repository.CategoryDetailRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper

/**
 * ViewModel cho trang chi tiết của một danh mục sản phẩm.
 * @param categoryDetailRepository Đối tượng Repository để truy xuất dữ liệu.
 * @param id ID của danh mục sản phẩm được chọn để hiển thị chi tiết.
 * @property categoryDetailLiveData LiveData chứa danh sách các sản phẩm trong danh mục được chọn.
 * @property detailSoftwareIdLiveData LiveData chứa ID của danh mục sản phẩm được chọn để hiển thị chi tiết.
 */
class CategoryDetailViewModel(
    categoryDetailRepository: CategoryDetailRepository,
    val id: Int
) :
    BaseViewModel() {
    // LiveData chứa danh sách các sản phẩm trong danh mục được chọn.
    val  categoryDetailLiveData = MutableLiveData<List<Software>>()

    // LiveData chứa ID của danh mục sản phẩm được chọn để hiển thị chi tiết.
    private val detailSoftwareIdLiveData = MutableLiveData<Int>()

    init {
        // Hiển thị tiến trình đang tải dữ liệu.
        progressbarLiveData.value = true

        // Thiết lập ID danh mục sản phẩm để truy xuất dữ liệu.
        detailSoftwareIdLiveData.value = id

        // Gọi phương thức lấy danh sách sản phẩm từ Repository.
        categoryDetailRepository.categoryDetail(detailSoftwareIdLiveData.value!!)
            .singleHelper()

            // Đóng bộ lọc xử lý tiến trình đang tải dữ liệu.
            .doFinally {
                progressbarLiveData.value = false
            }

            // Thực hiện việc gán dữ liệu sản phẩm vào LiveData khi đã lấy được từ Repository.
            .subscribe(object : Observer<List<Software>>(compositeDisposable) {
                override fun onSuccess(t: List<Software>) {
                    categoryDetailLiveData.value = t
                }
            })
    }
}