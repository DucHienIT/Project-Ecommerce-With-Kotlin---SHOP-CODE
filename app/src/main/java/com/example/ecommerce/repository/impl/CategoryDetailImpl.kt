package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Software
import com.example.ecommerce.repository.CategoryDetailRepository
import com.example.ecommerce.repository.datasource.CategoryDetailDataSource
import io.reactivex.rxjava3.core.Single

class CategoryDetailImpl(private val categoryDetailDataSource: CategoryDetailDataSource) :
    CategoryDetailRepository {
    override fun categoryDetail(id: Int): Single<List<Software>> =
        categoryDetailDataSource.categoryDetail(id)


}