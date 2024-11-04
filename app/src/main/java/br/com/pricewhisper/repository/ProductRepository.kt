package br.com.pricewhisper.repository

import br.com.pricewhisper.models.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class ProductRepository : IProductRepository {
    private val httpClient = OkHttpClient()
    private val gson = Gson()
    private val url: String = "https://omcorp-pricewhisper-default-rtdb.firebaseio.com"

    override fun getProductListFromFirebase(
        onRequestSuccess: (products: HashMap<String, Product?>?) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    ) {
        val request = Request.Builder()
            .url("$url/products.json")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onRequestFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body?.string() ?: ""
                if (localBody != "" && localBody != "null") {
                    val typeToken = object : TypeToken<HashMap<String, Product?>>() {}.type
                    val mapProduct: HashMap<String, Product?> =
                        gson.fromJson(localBody, typeToken)

                    onRequestSuccess(mapProduct)
                }
            }
        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    override fun getProductFromFirebaseById(
        id: String,
        onRequestSuccess: (productFound: Product?) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    ) {
        val request = Request.Builder()
            .url("$url/products/$id.json")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onRequestFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        onRequestSuccess(null)
                        return
                    }

                    val product: Product? =
                        gson.fromJson(response.body?.string(), Product::class.java)
                    onRequestSuccess(product)
                }
            }
        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    override fun postProductToFirebase(
        product: Product,
        onRequestSuccess: (productSaved: Product) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    ) {
        val json = gson.toJson(product)
        val body = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$url/products.json")
            .post(body)
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onRequestFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                onRequestSuccess(product)
            }
        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    override fun updateProductInFirebase(
        id: String,
        newProduct: Product,
        onRequestSuccess: (newProduct: Product?) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    ) {
        val json = gson.toJson(newProduct)
        val body = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$url/products/$id.json")
            .put(body)
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onRequestFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        onRequestSuccess(null)
                        return
                    }
                    val product: Product? =
                        gson.fromJson(response.body?.string(), Product::class.java)
                    onRequestSuccess(product)
                }
            }
        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    override fun deleteProductInFirebase(
        id: String,
        onRequestSuccess: (productDeleted: Product) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}