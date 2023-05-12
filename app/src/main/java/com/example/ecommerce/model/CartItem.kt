package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartItem(
    val id: Int,
    val software: Software,
    val quality: Int
): Parcelable