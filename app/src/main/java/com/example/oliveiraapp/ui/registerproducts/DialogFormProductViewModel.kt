package com.example.oliveiraapp.ui.registerproducts

import androidx.lifecycle.ViewModel
import com.example.oliveiraapp.firebase.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DialogFormProductViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow(DialogFormProductUiState())

    val uiState: StateFlow<DialogFormProductUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        nameProduct = it
                    )
                },
                onPriceChange = {
                    _uiState.value = _uiState.value.copy(
                        priceProduct = it
                    )
                },
                onQuantifyChange = {
                    _uiState.value = _uiState.value.copy(
                        quantifyProduct = it
                    )
                }
            )
        }

    }

   fun addProduct() {
        fireStoreRepository.addProduct(
            name = _uiState.value.nameProduct,
            description = "",
            image = "",
            price = _uiState.value.priceProduct.toDouble(),
            quantify = _uiState.value.quantifyProduct.toInt()
        )
    }
}

