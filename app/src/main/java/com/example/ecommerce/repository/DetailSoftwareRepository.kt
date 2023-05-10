package com.example.ecommerce.repository

import com.example.ecommerce.model.SoftwareDetail
import io.reactivex.rxjava3.core.Single

interface DetailSoftwareRepository {
    fun detailSoftware(id:Int): Single<SoftwareDetail>
}