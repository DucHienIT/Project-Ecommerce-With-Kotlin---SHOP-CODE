package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.SoftwareDetail
import com.example.ecommerce.repository.DetailSoftwareRepository
import com.example.ecommerce.repository.datasource.DetailSoftwareDataSource
import io.reactivex.rxjava3.core.Single

class DetailSoftwareRepositorylmpl(private val detailSoftwareDataSource: DetailSoftwareDataSource) :
    DetailSoftwareRepository {
    override fun detailSoftware(id:Int): Single<SoftwareDetail> =
        detailSoftwareDataSource.detailSoftware(id)
}