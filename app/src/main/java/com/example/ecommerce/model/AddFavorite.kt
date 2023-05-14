package com.example.ecommerce.model

data class AddFavorite(
    val id: Int,
    val is_favorite: Boolean,
    val Software: Int,
    val user: Int,
    val available: String
)