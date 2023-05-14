package com.example.ecommerce.model_chatgpt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GenerateTextResponse(
    val id: String,
    val objects: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>
)
{
    data class Choice(
        val text: String,
        val index: Int,
        val logprobs: Logprobs,
        val finish_reason: String
    )
    {
        data class Logprobs(
            val tokens: List<String>,
            val token_logprobs: List<Double>,
            val top_logprobs: List<Double>,
            val text_offset: List<Int>
        )
    }
}
