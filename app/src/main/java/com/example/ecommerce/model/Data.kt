package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val id: Int,
    val key: Keycode,
    val quality: Int
): Parcelable