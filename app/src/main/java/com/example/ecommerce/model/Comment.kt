package com.example.ecommerce.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Time

@Parcelize
data class Comment(
    val id: Int,
    val user: String,
    val software: Software,
    val text: String,
    val created_at: String
): Parcelable