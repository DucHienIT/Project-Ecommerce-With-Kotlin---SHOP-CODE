package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class OrderDetail(
    val status: String,
    val id: Int,
    val message: String,
    val data: List<Data>
)