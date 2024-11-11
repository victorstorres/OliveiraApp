package com.example.oliveiraapp.ui.home

import com.example.oliveiraapp.data.Product


data class HomeUiState(
    val nameProduct: String = "",
    val priceProduct: String = "",
    val quentifyProduct: String = "",
    val listProducts : List<Product> = emptyList()

)