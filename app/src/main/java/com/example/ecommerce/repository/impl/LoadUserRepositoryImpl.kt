package com.example.ecommerce.repository.impl

import com.example.ecommerce.model.Software
import com.example.ecommerce.model.User
import com.example.ecommerce.repository.AmazingRepository
import com.example.ecommerce.repository.LoadUserRepository
import com.example.ecommerce.repository.datasource.AmazingDataSource
import com.example.ecommerce.repository.datasource.LoadUserDataSource
import io.reactivex.rxjava3.core.Single

class LoadUserRepositoryImpl(private val loadUserDataSource: LoadUserDataSource) : LoadUserRepository {
    override fun loadUser(): Single<User> = loadUserDataSource.loadUser()
}