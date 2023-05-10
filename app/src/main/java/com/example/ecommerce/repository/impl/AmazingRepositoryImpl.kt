package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Software
import com.example.ecommerce.repository.AmazingRepository
import com.example.ecommerce.repository.datasource.AmazingDataSource
import io.reactivex.rxjava3.core.Single

class AmazingRepositoryImpl(private val amazingDataSource: AmazingDataSource) : AmazingRepository {
    override fun amazing(): Single<List<Software>> = amazingDataSource.amazing()


}