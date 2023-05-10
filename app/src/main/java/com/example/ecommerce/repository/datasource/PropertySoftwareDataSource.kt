package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Property
import io.reactivex.rxjava3.core.Single

interface PropertySoftwareDataSource {
    fun propertySoftware(id: Int): Single<List<Property>>
}