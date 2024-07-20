package com.example.mvitest.uILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvitest.data.repo.IProductRepo
import com.example.mvitest.uILayer.allProduct.ProductViewModel
import com.example.mvitest.uILayer.favourite.FavouriteViewModel

class ViewModelFactory(private val repo: IProductRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> ProductViewModel(repo) as T
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> FavouriteViewModel(repo) as T
            else -> throw IllegalArgumentException("View model class not found")
        }
    }
}