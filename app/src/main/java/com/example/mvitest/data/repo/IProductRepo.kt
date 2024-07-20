package com.example.mvitest.data.repo

import com.example.mvitest.data.domain.Products

interface IProductRepo {
    suspend fun getAllProduct(): MutableList<Products>

    suspend fun getStoredProduct(): List<Products>

    suspend fun insertProduct(products: Products)

    suspend fun deleteProduct(products: Products)
}