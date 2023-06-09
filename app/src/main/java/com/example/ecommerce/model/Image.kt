package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val id: Int,
    val image: String,
    val Software: Int,
    val title: String
):Parcelable