package com.example.ecommerce.model_chatgpt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ModelDetails(
    val id: String,
    val name: String,
    val description: String,
    val trained: Boolean,
    val dataSet: String,
    val created: Long,
    val domain: String,
    val owner: String,
    val language: String,
    val architecture: String,
    val modelType: String
)
