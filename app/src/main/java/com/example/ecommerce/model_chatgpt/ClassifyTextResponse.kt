package com.example.ecommerce.model_chatgpt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ClassifyTextResponse(
    val document: DocumentClass,
    val model: String,
    val objects: String,
    val text: String
)