package com.example.mvitest.data.remoteDataSource

import com.example.mvitest.data.domain.UpperClassPojo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<UpperClassPojo>
}