package com.example.ecommerce.repository

import com.example.ecommerce.model.Property
import io.reactivex.rxjava3.core.Single

interface PropertySoftwareRepository {
    fun propertySoftware(id: Int): Single<List<Property>>
}