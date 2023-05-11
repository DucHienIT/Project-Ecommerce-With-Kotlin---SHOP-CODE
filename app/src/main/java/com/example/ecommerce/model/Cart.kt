package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cart(
    val id: Int,
    val cartItem: CartItem,
    val user: Int
): Parcelable