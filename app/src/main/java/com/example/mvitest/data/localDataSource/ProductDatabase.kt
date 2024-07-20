package com.example.mvitest.data.localDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvitest.data.domain.Products

@Database(entities = [Products::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null
        fun getInstance(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(context){
                val instance = Room.databaseBuilder(
                    context.applicationContext, ProductDatabase::class.java,"product_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}