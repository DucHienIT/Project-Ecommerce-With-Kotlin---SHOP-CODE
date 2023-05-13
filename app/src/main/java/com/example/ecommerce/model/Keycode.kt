package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Keycode(
    val id: Int,
    val key_code: String,
    val software: Software,
    val is_used: Boolean
): Parcelable