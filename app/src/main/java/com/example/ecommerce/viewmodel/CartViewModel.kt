package com.example.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.model.AddFavorite
import com.example.ecommerce.model.CartItem
import com.example.ecommerce.model.Category
import com.example.ecommerce.repository.AddCartRepository
import com.example.ecommerce.repository.CartRepository
import com.example.ecommerce.utils.BaseViewModel
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.utils.singleHelper
import java.math.BigDecimal


// Lớp AddFavoriteViewModel là một ViewModel trong kiến trúc MVVM được sử dụng để xử lý dữ liệu và logic liên quan đến hành động thêm sản phẩm vào danh sách yêu thích của người dùng.
class CartViewModel(private val cartRepository: CartRepository) : BaseViewModel() {
    // đối tượng MutableLiveData để truyền dữ liệu giữa lớp ViewModel và View (Activity hoặc Fragment)

    val cartLiveData = MutableLiveData<List<CartItem>>()

    // Phương thức để thêm sản phẩm vào danh sách yêu thích
    fun cart() {

        // Đặt giá trị của đối tượng progressbarLiveData là true để hiển thị tiến trình đang xử lý
        progressbarLiveData.value = true
        cartRepository.cart("Bearer ${TokenHolder.access_token}")
            .singleHelper()
            // Không quan tâm đến kết quả trả về khi hành động kết thúc, đặt giá trị của progressbarLiveData là false để ẩn tiến trình
            .doFinally {
                progressbarLiveData.value = false
            }
            // Xử lý kết quả trả về khi thêm sản phẩm vào danh sách yêu thích thành công
            .subscribe(object : Observer<List<CartItem>>(compositeDisposable) {
                override fun onSuccess(t: List<CartItem>) {
                    cartLiveData.value = t

                }
                override fun onError(e: Throwable?) {
                    super.onError(e)
                    println("that bai")
                }
            })
    }
    fun fetchSubCart(access_token: String, cartRepository: CartRepository) {
        // Đặt giá trị của đối tượng progressbarLiveData là true để hiển thị tiến trình đang xử lý
        progressbarLiveData.value = true
        cartRepository.cart(access_token)
            .singleHelper()
            // Không quan tâm đến kết quả trả về khi hành động kết thúc, đặt giá trị của progressbarLiveData là false để ẩn tiến trình
            .doFinally {
                progressbarLiveData.value = false
            }
            // Xử lý kết quả trả về khi fetch sản phẩm thành công
            .subscribe(object : Observer<List<CartItem>>(compositeDisposable) {
                override fun onSuccess(t: List<CartItem>) {
                    cartLiveData.value = t!!
                }
            })
    }

}