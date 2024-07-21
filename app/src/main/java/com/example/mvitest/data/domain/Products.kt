package com.example.mvitest.data.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product_table")
data class Products(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val brand: String?,
    @SerializedName("thumbnail")
    @ColumnInfo(name = "url")
    val url: String,
    val price: Double,
    val rating: Float
)