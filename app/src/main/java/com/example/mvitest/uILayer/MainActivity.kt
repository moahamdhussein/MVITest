package com.example.mvitest.uILayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvitest.databinding.ActivityMainBinding
import com.example.mvitest.uILayer.allProduct.ProductList
import com.example.mvitest.uILayer.favourite.FavouriteList

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnProductList.setOnClickListener {
            startActivity(Intent(this,ProductList::class.java))
        }
        binding.btnFav.setOnClickListener {
            startActivity(Intent(this,FavouriteList::class.java))
        }

    }
}