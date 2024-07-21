package com.example.mvitest.data.localDataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvitest.data.domain.Products

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    suspend fun getAllProduct(): List<Products>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: Products)

    @Delete
    suspend fun deleteProduct(products: Products)
}