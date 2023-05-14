package com.example.ecommerce.model_chatgpt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GenerateTextRequest(
    val prompt: String,
    val max_tokens: Int,
    val n: Int,
    val temperature: Double
)

