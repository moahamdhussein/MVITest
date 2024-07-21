package com.example.mvitest.uILayer.allProduct

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvitest.data.domain.Products
import com.example.mvitest.data.repo.IProductRepo
import com.example.mvitest.uILayer.ProductIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val TAG = "AllProductViewModel"
class ProductViewModel(private val repo: IProductRepo) : ViewModel() {
    private val _state = MutableStateFlow(ProductState<Products>(remoteProduct = emptyList()))
    val state: StateFlow<ProductState<Products>> = _state


    fun handleIntent(intent: ProductIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is ProductIntent.LoadProduct -> fetchProduct()
                is ProductIntent.SaveProduct -> saveProduct(intent.products)
                is ProductIntent.DeleteProduct -> {}
                is ProductIntent.LoadFavouriteProduct -> {}
            }
        }
    }

    private suspend fun saveProduct(products: Products) {
        repo.insertProduct(products)
    }

    private suspend fun fetchProduct() {
        Log.i(TAG, "fetchProduct: ")
        _state.value = _state.value.copy(loading = true, error = null)

        val products = repo.getAllProduct()
        _state.value = ProductState(loading = false, remoteProduct = products)

    }



}