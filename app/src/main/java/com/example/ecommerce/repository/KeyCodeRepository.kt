package com.example.ecommerce.repository

import com.example.ecommerce.model.Keycode
import com.example.ecommerce.model.Price
import io.reactivex.rxjava3.core.Single

interface KeyCodeRepository {
    fun keyCode(id:Int):Single<Keycode>
}