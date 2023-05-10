package com.example.ecommerce.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Category (
    val id: Int,
    val name: String,
): Parcelable