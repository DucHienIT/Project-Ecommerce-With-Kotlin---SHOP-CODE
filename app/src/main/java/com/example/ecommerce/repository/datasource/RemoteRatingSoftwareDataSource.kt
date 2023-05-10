package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Rating
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteRatingSoftwareDataSource(private val apiService: ApiService) : RatingSoftwareDataSource {
    override fun ratingSoftware(id: Int): Single<List<Rating>> = apiService.ratingSoftware(id)


}