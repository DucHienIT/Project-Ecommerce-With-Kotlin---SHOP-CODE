package com.example.ecommerce.model_chatgpt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Model(
    val id: String,
    val objects: String,
    val created: Long,
    val modelDetails: ModelDetails
)