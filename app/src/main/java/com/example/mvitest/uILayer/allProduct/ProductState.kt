package com.example.mvitest.uILayer.allProduct

import com.example.mvitest.data.domain.Products

data class ProductState (
    var loading : Boolean  = false,
    var remoteProduct :  List<Products> = emptyList(),
    var error  : String? = null
    )