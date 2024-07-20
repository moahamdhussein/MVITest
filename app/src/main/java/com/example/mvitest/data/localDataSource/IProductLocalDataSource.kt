package com.example.mvitest.data.localDataSource

import com.example.mvitest.data.domain.Products

interface IProductLocalDataSource {
    suspend fun insertProduct(product: Products)
    suspend fun deleteProduct(product: Products)
    suspend fun getStoredProduct(): List<Products>
}