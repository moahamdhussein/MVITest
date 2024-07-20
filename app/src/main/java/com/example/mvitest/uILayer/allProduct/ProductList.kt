package com.example.mvitest.uILayer.allProduct

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvitest.data.domain.Products
import com.example.mvitest.data.localDataSource.ProductLocalDataSource
import com.example.mvitest.data.remoteDataSource.ProductRemoteDataSource
import com.example.mvitest.data.repo.ProductRepo
import com.example.mvitest.databinding.ActivityAllProductListBinding
import com.example.mvitest.uILayer.ProductIntent
import com.example.mvitest.uILayer.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "AllProductList"

class ProductList : AppCompatActivity() , IProductsList{
    private val binding: ActivityAllProductListBinding by lazy {
        ActivityAllProductListBinding.inflate(
            layoutInflater
        )
    }
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: ProductListAdapter
    private val viewModel by lazy {
        val factory =
            ViewModelFactory(
                ProductRepo.getInstance(
                    ProductRemoteDataSource.getInstance(),
                    ProductLocalDataSource(context = this)
                )
            )
        ViewModelProvider(this, factory)[ProductViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = ProductListAdapter(emptyList(),this)
        binding.myRecyclerView.adapter = adapter
        manager = LinearLayoutManager(this)
        manager.orientation = RecyclerView.HORIZONTAL
        binding.myRecyclerView.adapter = this.adapter
        binding.myRecyclerView.layoutManager = manager

        viewModel.handleIntent(ProductIntent.LoadProduct)

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.state.collectLatest { response ->
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

    override fun saveProduct(product: Products) {
        viewModel.handleIntent(ProductIntent.SaveProduct(product))
        Log.i(TAG, "saveProduct: saved")
    }
}