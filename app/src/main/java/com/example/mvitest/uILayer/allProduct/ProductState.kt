package com.example.mvitest.uILayer.allProduct

import com.example.mvitest.data.domain.Products

data class  ProductState<T> (
    var loading : Boolean  = false,
    var remoteProduct :  List<T>,
    var error  : String? = null
    )