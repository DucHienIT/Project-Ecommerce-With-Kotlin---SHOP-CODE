package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.SoftwareDetail
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteDetailDataSource(private val apiService: ApiService) : DetailSoftwareDataSource {
    override fun detailSoftware(id: Int): Single<SoftwareDetail> = apiService.detailSoftware(id)


}