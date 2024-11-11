package com.example.oliveiraapp.firebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.oliveiraapp.data.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
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
        name: String,
        description: String,
        image: String,
        price: Double,
        quantify: Int
    ) {
        val mapProduct = mapOf<String, Any>(
            product.name to name,
            product.price to price,
            product.description to description,
            product.image to image,
            product.quantify to quantify
        )
        fireStore.collection(COLLECTION_NAME).add(mapProduct).addOnSuccessListener { }

    }

    fun searchProduct(): Flow<List<Product>> {
        val liveData = MutableLiveData<List<Product>>()
        fireStore.collection(COLLECTION_NAME)
            .addSnapshotListener { s, _ ->
                s?.let { snapshot ->
                    val product = mutableListOf<Product>()
                    for (document in snapshot.documents) {
                        val documentProduct = document.toObject<ProductDocuments>()
                        documentProduct?.let { ProductNotNull ->
                            product.add(ProductNotNull.forProduct())
                        }
                    }
                    liveData.value = product
                }
            }
        return liveData.asFlow()
    }

    private class ProductDocuments(
        val name: String = "",
        val description: String = "",
        val image: String = "",
        val price: Double = 0.0,
        val quantify: Int = 0,
    ) {
        fun forProduct() = Product(
            name = name,
            description = description,
            image = image,
            price = price,
            quantify = quantify
        )

    }
}