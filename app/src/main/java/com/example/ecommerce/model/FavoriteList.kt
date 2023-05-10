package com.example.ecommerce.model

data class FavoriteList(
    val id: Int,
    val is_favorite: Boolean,
    val software: Software,
    val user: Int
)