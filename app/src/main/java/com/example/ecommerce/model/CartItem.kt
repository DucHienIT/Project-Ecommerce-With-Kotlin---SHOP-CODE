package com.example.ecommerce.model

import android.os.Parcelable


// Lớp mô tả một mặt hàng trong giỏ hàng
data class CartItem(
    val id: Int,
    val  software: Software,
    val  quantity: Int,

): java.io.Serializable