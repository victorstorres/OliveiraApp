package com.example.oliveiraapp.ui.registerproducts

data class DialogFormProductUiState(
    val nameProduct: String = "",
    val onNameChange: (String) -> Unit = {},
    val priceProduct: String = "0",
    val onPriceChange: (String) -> Unit = {},
    val quantifyProduct: String = "0",
    val onQuantifyChange: (String) -> Unit = {},

    )
