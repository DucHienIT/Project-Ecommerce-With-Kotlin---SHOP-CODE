package com.example.ecommerce.model


data class Order(
    val id: Int,
    val user: String,
    val order_date: String,
    val status: String,
)