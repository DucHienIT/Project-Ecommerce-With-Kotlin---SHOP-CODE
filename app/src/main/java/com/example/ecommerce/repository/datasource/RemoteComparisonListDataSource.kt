package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Software
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteComparisonListDataSource(private val apiService: ApiService):ComparisonListDataSource {
    override fun comparisonSoftware(id: Int): Single<List<Software>> = apiService.comparisonSoftware(id)


}