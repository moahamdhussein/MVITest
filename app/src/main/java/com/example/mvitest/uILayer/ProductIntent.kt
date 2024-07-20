package com.example.mvitest.uILayer

import com.example.mvitest.data.domain.Products

sealed interface ProductIntent {
    data object LoadProduct: ProductIntent
    data object LoadFavouriteProduct : ProductIntent
    data class SaveProduct(val products: Products) : ProductIntent
    data class DeleteProduct(val products: Products) : ProductIntent
}