package com.example.mvitest.uILayer.favourite

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvitest.data.domain.Products
import com.example.mvitest.data.localDataSource.ProductLocalDataSource
import com.example.mvitest.data.remoteDataSource.ProductRemoteDataSource
import com.example.mvitest.data.repo.ProductRepo
import com.example.mvitest.databinding.ActivityFavouriteBinding
import com.example.mvitest.uILayer.ProductIntent
import com.example.mvitest.uILayer.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "FavouriteList"
class FavouriteList : AppCompatActivity() , IFavouriteList {
    private val binding: ActivityFavouriteBinding by lazy {
        ActivityFavouriteBinding.inflate(
            layoutInflater
        )
    }
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: FavouriteListAdapter
    private val viewModel by lazy {
        val factory =
            ViewModelFactory(
                ProductRepo.getInstance(
                    ProductRemoteDataSource.getInstance(),
                    ProductLocalDataSource(context = this)
                )
            )
        ViewModelProvider(this, factory)[FavouriteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        adapter = FavouriteListAdapter(emptyList(),this)
        binding.myRecyclerView.adapter = adapter
        manager = LinearLayoutManager(this)
        manager.orientation = RecyclerView.HORIZONTAL
        binding.myRecyclerView.adapter = this.adapter
        binding.myRecyclerView.layoutManager = manager

        viewModel.handleIntent(ProductIntent.LoadFavouriteProduct)

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.state.collectLatest {response->
                if (response.loading) {
                    Log.i(TAG, "onCreate: Loading")
                } else if (response.error != null) {
                    Log.i(TAG, "onCreate: Error ${response.error}")
                } else {
                    Log.i(TAG, "onCreate: done data come")
                    withContext(Dispatchers.Main) {
                        adapter.setList(response.remoteProduct)
                    }
                }
            }
        }
    }

    override fun deleteProduct(product: Products) {
        viewModel.handleIntent(ProductIntent.DeleteProduct(product))
    }
}