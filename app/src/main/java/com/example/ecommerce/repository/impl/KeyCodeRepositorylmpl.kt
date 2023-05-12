package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Keycode
import com.example.ecommerce.model.Price
import com.example.ecommerce.repository.KeyCodeRepository
import com.example.ecommerce.repository.PriceSoftwareRepository
import com.example.ecommerce.repository.datasource.KeyCodeDataSource
import com.example.ecommerce.repository.datasource.PriceSoftwareDataSource
import io.reactivex.rxjava3.core.Single

class KeyCodeRepositorylmpl(private val keyCodeDataSource: KeyCodeDataSource): KeyCodeRepository {
    override fun keyCode(id: Int): Single<Keycode> = keyCodeDataSource.keyCode(id)
}