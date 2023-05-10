package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Price
import com.example.ecommerce.repository.PriceSoftwareRepository
import com.example.ecommerce.repository.datasource.PriceSoftwareDataSource
import io.reactivex.rxjava3.core.Single

class PriceSoftwareRepositorylmpl(private val priceSoftwareDataSource: PriceSoftwareDataSource):PriceSoftwareRepository {
    override fun priceSoftware(id: Int): Single<List<Price>> = priceSoftwareDataSource.priceSoftware(id)


}