package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Order(
    val id: Int,
    val user: String,
    val order_date: String,
    val status: String
): Parcelable