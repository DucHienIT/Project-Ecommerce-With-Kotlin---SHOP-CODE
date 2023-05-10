package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.SoftwareDetail
import io.reactivex.rxjava3.core.Single

interface DetailSoftwareDataSource {
    fun detailSoftware(id:Int): Single<SoftwareDetail>

}