package com.example.ecommerce.repository

import com.example.ecommerce.model.Software
import io.reactivex.rxjava3.core.Single

interface AmazingRepository {
    fun amazing(): Single<List<Software>>
}