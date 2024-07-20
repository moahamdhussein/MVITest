package com.example.mvitest.data.remoteDataSource

import android.util.Log
import com.example.mvitest.data.domain.Products

private const val TAG = "ProductRemoteDataSource"
class ProductRemoteDataSource  private constructor() : IProductRemoteDataSource {
    private val productService: ApiService by lazy {
        RetrofitHelper.getInstance().create(ApiService::class.java)
    }
    override suspend fun getAllProduct(): MutableList<Products> {
        Log.i(TAG, "getAllProduct: ")
        return productService.getProducts().body()?.products ?: mutableListOf()
    }
    companion object{
        private var instance: ProductRemoteDataSource? = null
        fun getInstance(): ProductRemoteDataSource {
            return instance ?: synchronized(this){
                val temp = ProductRemoteDataSource()
                instance =temp
                temp
            }
        }
    }
}