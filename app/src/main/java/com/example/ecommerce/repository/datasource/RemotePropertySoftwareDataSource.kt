package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Property
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemotePropertySoftwareDataSource(private val apiService: ApiService) :
    PropertySoftwareDataSource {
    override fun propertySoftware(id: Int): Single<List<Property>> = apiService.propertySoftware(id)


}