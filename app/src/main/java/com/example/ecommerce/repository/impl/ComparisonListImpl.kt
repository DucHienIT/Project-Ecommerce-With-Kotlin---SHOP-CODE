package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Software
import com.example.ecommerce.repository.ComparisonListRepository
import com.example.ecommerce.repository.datasource.ComparisonListDataSource
import io.reactivex.rxjava3.core.Single

class ComparisonListImpl(private val dataSource: ComparisonListDataSource) :
    ComparisonListRepository {
    override fun comparisonSoftware(id: Int): Single<List<Software>> =
        dataSource.comparisonSoftware(id)
}