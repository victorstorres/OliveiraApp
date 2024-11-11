package com.example.oliveiraapp.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.oliveiraapp.data.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


private const val COLLECTION_NAME = "products"

class FireStoreRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
) {

    private object product {
        const val name = "name"
        const val price = "price"
        const val description = "description"
        const val image = "image"
        const val quantify = "quantify"
    }

    fun addProduct(
        product: Product
    ) {
        val produtoDocumento = ProductDocuments(
            name = product.name,
            description = product.description,
            image = product.image,
            price = product.price,
            quantify = product.quantify
        )

        val document = fireStore.collection(COLLECTION_NAME)
            .document()

        document.set(produtoDocumento)
    }

    fun searchProduct(): Flow<List<Product>> = MutableStateFlow<List<Product>>(emptyList()).apply {
        fireStore.collection(COLLECTION_NAME)
            .addSnapshotListener { s, _ ->
                s?.let { snapshot ->
                    val produtos: List<Product> = snapshot.documents
                        .mapNotNull { documento ->
                            documento.toObject<ProductDocuments>()?.forProduct(documento.id)
                        }
                    value = produtos
                }
            }
    }

    fun searchProductForId(id: String) : Flow<Product> = MutableStateFlow(Product()).apply {
        fireStore.collection(COLLECTION_NAME).document(id)
            .addSnapshotListener { s, _ ->
                s?.let { document ->
                    document.toObject<ProductDocuments>()?.forProduct(document.id)?.let { product ->
                        value = product
                    }
                }
            }
    }

    class ProductDocuments(
        val name: String = "",
        val description: String = "",
        val image: String = "",
        val price: Double = 0.0,
        val quantify: Int = 0,
    ) {
        fun forProduct(id: String?) = Product(
            id = id,
            name = name,
            description = description,
            image = image,
            price = price,
            quantify = quantify
        )

    }
}