package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Property
import com.example.ecommerce.repository.PropertySoftwareRepository
import com.example.ecommerce.repository.datasource.PropertySoftwareDataSource
import io.reactivex.rxjava3.core.Single

class PropertySoftwareImpl(private val propertySoftwareDataSource: PropertySoftwareDataSource) :
    PropertySoftwareRepository {
    override fun propertySoftware(id: Int): Single<List<Property>> =
        propertySoftwareDataSource.propertySoftware(id)


}