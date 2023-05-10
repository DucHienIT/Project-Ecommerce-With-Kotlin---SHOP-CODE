package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Software(
    val id: Int,
    val name: String,
    val description: String,
    val image_url: String,
    val price: String,
    val quantity: Int,
    val category: Int,
    val has_key: Boolean
): Parcelable