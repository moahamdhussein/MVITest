package com.example.mvitest.data.domain



data class UpperClassPojo(
    var products: MutableList<Products>,
    var total: Int,
    var skip: Int,
    var limit: Int,
)