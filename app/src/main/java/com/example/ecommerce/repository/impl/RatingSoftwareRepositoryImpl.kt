package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Rating
import com.example.ecommerce.repository.RatingSoftwareRepository
import com.example.ecommerce.repository.datasource.RatingSoftwareDataSource
import io.reactivex.rxjava3.core.Single

class RatingSoftwareRepositoryImpl(private val ratingSoftwareDataSource: RatingSoftwareDataSource) :
    RatingSoftwareRepository {
    override fun ratingSoftware(id: Int): Single<List<Rating>> =
        ratingSoftwareDataSource.ratingSoftware(id)


}