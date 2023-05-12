package com.example.ecommerce.repository.datasource

import com.example.ecommerce.model.Keycode
import io.reactivex.rxjava3.core.Single

interface KeyCodeDataSource {
    fun keyCode(id:Int): Single<Keycode>
}