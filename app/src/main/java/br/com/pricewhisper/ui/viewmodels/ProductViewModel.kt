package br.com.pricewhisper.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repo = ProductRepository()
    val productList = mutableStateListOf<Product>()
    val currentProduct = mutableStateOf<Product?>(null)

    fun loadProducts() {
        viewModelScope.launch {
            repo.getProductListFromFirebase(
                onRequestFailure = { error ->
                    Log.e(
                        "PRICE_WHISPER",
                        "GET PRODUCT LIST FROM FIREBASE FAILED:\n${error.message}"
                    )
                },
                onRequestSuccess = { products ->
                    productList.clear()
                    products?.forEach { (id, product) ->
                        if (product != null) {
                            product.id = id
                            productList.add(product)
                        }
                    }
                }
            )
        }
    }

    fun saveProduct(
        product: Product
    ) {
        viewModelScope.launch {
            repo.postProductToFirebase(
                product = product,
                onRequestFailure = { error ->
                    Log.e(
                        "PRICE_WHISPER",
                        "POST PRODUCT TO FIREBASE FAILED:\n${error.message}"
                    )
                },
                onRequestSuccess = { productSaved ->
                    Log.d("PRICE_WHISPER", "Product saved: $productSaved")
                }
            )
        }
    }

    fun editProduct(
        id: String,
        newProduct: Product,
    ) {
        viewModelScope.launch {
            repo.updateProductInFirebase(
                id = id,
                newProduct = newProduct,
                onRequestFailure = { error ->
                    Log.e(
                        "PRICE_WHISPER",
                        "EDIT PRODUCT FROM FIREBASE FAILED:\n${error.message}"
                    )
                },
                onRequestSuccess = { product ->
                    Log.d("PRICE_WHISPER", "Product Updated: $product")
                }
            )
        }
    }

    fun deleteProduct(
        id: String,
        onResult: (deletedProduct: Product?) -> Unit
    ) {
        viewModelScope.launch {
            repo.deleteProductInFirebase(
                id = id,
                onRequestFailure = { error ->
                    Log.e(
                        "PRICE_WHISPER",
                        "DELETE PRODUCT FROM FIREBASE FAILED:\n${error.message}"
                    )
                },
                onRequestSuccess = { onResult(it) }
            )
        }
    }
}