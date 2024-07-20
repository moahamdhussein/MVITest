package com.example.mvitest.uILayer.allProduct

import com.example.mvitest.data.domain.Products

sealed class ProductAction {

    object LoadProductAction : ProductAction()
    data class SaveProduct(val products: Products) : ProductAction()

}