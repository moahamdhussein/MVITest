package com.example.mvitest.uILayer.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvitest.data.domain.Products
import com.example.mvitest.data.repo.IProductRepo
import com.example.mvitest.uILayer.ProductIntent
import com.example.mvitest.uILayer.allProduct.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repo: IProductRepo) : ViewModel() {

    private val _state = MutableStateFlow(ProductState<Products>(remoteProduct = emptyList()))
    val state: StateFlow<ProductState<Products>> = _state


    fun handleIntent(intent: ProductIntent) {
        viewModelScope.launch {
            when (intent) {
                is ProductIntent.LoadProduct -> {}
                is ProductIntent.SaveProduct -> {}
                is ProductIntent.DeleteProduct -> deleteProduct(intent.products)
                is ProductIntent.LoadFavouriteProduct -> fetchProduct()
            }
        }
    }

    private suspend fun deleteProduct(products: Products) {
        repo.deleteProduct(products)
    }

    private suspend fun fetchProduct() {
        _state.value  = _state.value.copy(loading = true, error = null)
        val products = repo.getStoredProduct()
        _state.value = ProductState(loading = false, remoteProduct = products)

    }

}
