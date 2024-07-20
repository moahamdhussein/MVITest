package com.example.mvitest.data.repo

import com.example.mvitest.data.localDataSource.IProductLocalDataSource
import com.example.mvitest.data.domain.Products
import com.example.mvitest.data.remoteDataSource.IProductRemoteDataSource

class ProductRepo private constructor(
    private var remoteDataSource: IProductRemoteDataSource,
    private var localDataSource: IProductLocalDataSource
) : IProductRepo {
    companion object {
        private var instance: ProductRepo? = null

        fun getInstance(
            remoteDataSource: IProductRemoteDataSource,
            localDataSource: IProductLocalDataSource
        ): ProductRepo {
            return instance ?: synchronized(this) {
                val temp = ProductRepo(
                    remoteDataSource = remoteDataSource,
                    localDataSource = localDataSource
                )
                instance = temp
                temp
            }
        }
    }

    override suspend fun getAllProduct(): MutableList<Products> {
        return remoteDataSource.getAllProduct()
    }

    override suspend fun getStoredProduct(): List<Products> {
        return localDataSource.getStoredProduct()
    }

    override suspend fun insertProduct(products: Products) {
        localDataSource.insertProduct(products)
    }

    override suspend fun deleteProduct(products: Products) {
        localDataSource.deleteProduct(products)
    }
}