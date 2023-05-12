package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Keycode
import com.example.ecommerce.model.Price
import com.example.ecommerce.network.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteKeyCodeDataSource(private val apiService: ApiService) : KeyCodeDataSource {
    override fun keyCode(id: Int): Single<Keycode> = apiService.keyCode(id)

}