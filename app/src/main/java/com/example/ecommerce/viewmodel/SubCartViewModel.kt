package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.AddFavorite
import com.example.ecommerce.model.CartItem
import com.example.ecommerce.repository.AddCartRepository
import com.example.ecommerce.repository.SubCartRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.singleHelper
import retrofit2.http.Header
import java.math.BigDecimal


// Lớp AddFavoriteViewModel là một ViewModel trong kiến trúc MVVM được sử dụng để xử lý dữ liệu và logic liên quan đến hành động thêm sản phẩm vào danh sách yêu thích của người dùng.
class SubCartViewModel(private val subCartRepository: SubCartRepository, ) : BaseViewModel() {
    // đối tượng MutableLiveData để truyền dữ liệu giữa lớp ViewModel và View (Activity hoặc Fragment)
    val addCartLiveData = MutableLiveData<AddCartMessage>()
    // Phương thức để thêm sản phẩm vào danh sách yêu thích
    fun subCart(id: Int,access_token: String) {
        // Đặt giá trị của đối tượng progressbarLiveData là true để hiển thị tiến trình đang xử lý
        progressbarLiveData.value = true
        subCartRepository.subCart(id,access_token)
            .singleHelper()
            // Không quan tâm đến kết quả trả về khi hành động kết thúc, đặt giá trị của progressbarLiveData là false để ẩn tiến trình
            .doFinally {
                progressbarLiveData.value = false
            }
            // Xử lý kết quả trả về khi thêm sản phẩm vào danh sách yêu thích thành công
            .subscribe(object : Observer<AddCartMessage>(compositeDisposable) {
                override fun onSuccess(t: AddCartMessage) {
                    addCartLiveData.value = t!!
                }

            })
    }
}