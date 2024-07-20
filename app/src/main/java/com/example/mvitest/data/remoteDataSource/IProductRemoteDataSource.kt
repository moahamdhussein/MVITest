package com.example.mvitest.data.remoteDataSource

import com.example.mvitest.data.domain.Products

interface IProductRemoteDataSource {
    suspend fun getAllProduct(): MutableList<Products>
}