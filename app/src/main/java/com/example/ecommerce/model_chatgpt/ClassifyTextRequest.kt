package com.example.ecommerce.model_chatgpt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ClassifyTextRequest(
    val model: String,
    val query: String,
    val examples: List<ClassifyExample>,
    val search_model: String? = null,
    val model_bias: Double? = null,
    val temperature: Double? = null,
    val logprobs: Int? = null,
    val max_examples: Int? = null,
    val return_prompt: Boolean? = null,
    val return_metadata: Boolean? = null
)
