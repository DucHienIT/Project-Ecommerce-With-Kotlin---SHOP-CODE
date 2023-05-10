package com.example.ecommerce.repository

import com.example.ecommerce.model.Rating
import io.reactivex.rxjava3.core.Single

interface RatingSoftwareRepository {
    fun ratingSoftware(id:Int):Single<List<Rating>>
}