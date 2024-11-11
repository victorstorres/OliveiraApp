package com.example.oliveiraapp.data


data class Product(
    var id: String? = null,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val price: Double = 0.0,
    val quantify: Int = 0,

)
