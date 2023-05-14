package com.example.ecommerce.model_chatgpt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ClassifyExample(
    val document: String,
    val label: String
)

