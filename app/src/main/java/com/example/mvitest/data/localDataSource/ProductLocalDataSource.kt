package com.example.mvitest.data.localDataSource

import android.content.Context
import android.util.Log
import com.example.mvitest.data.domain.Products


private const val TAG = "ProductLocalDataSource"
class ProductLocalDataSource(context : Context) : IProductLocalDataSource {
    private val dao : ProductDao by lazy {
        val appDatabase = ProductDatabase.getInstance(context)
        appDatabase.getProductDao()
    }

    override suspend fun insertProduct(product: Products){
        Log.i(TAG, "insertProduct: saved ${product.title}")
        dao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: Products){
        dao.deleteProduct(product)
    }
    override suspend fun getStoredProduct():List<Products>{
        Log.i(TAG, "getStoredProduct: saved done")
       return dao.getAllProduct()
    }
}