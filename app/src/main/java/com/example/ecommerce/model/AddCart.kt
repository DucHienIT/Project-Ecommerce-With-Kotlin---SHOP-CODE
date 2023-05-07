package com.example.ecommerce.model


// Lớp mô tả một mặt hàng trong giỏ hàng
data class AddCart(
    val count: Int,
    val id: Int,
    val is_add: Boolean,
    val pay: Boolean,
    val price: Int,
    val product: Product,
    val update: Boolean,
)