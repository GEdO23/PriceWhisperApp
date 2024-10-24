package br.com.pricewhisper.repository

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
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

class ProductRepository {
    private val httpClient = OkHttpClient()
    private val gson = Gson()
    private val url: String = "https://omcorp-pricewhisper-default-rtdb.firebaseio.com"

    fun loadFirebase(productList: SnapshotStateList<Product>) {
        val request = Request.Builder()
            .url("$url/products.json")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PRICE_WHISPER", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body?.string() ?: ""
                Log.d("PRICE_WHISPER", localBody)
                if (localBody != "" && localBody != "null") {
                    val typeToken = object : TypeToken<HashMap<String?, Product?>>() {}.type
                    val mapProduct: HashMap<String?, Product?> =
                        gson.fromJson(localBody, typeToken)

                    productList.clear()
                    mapProduct.forEach { (id, product) ->
                        if (product != null) {
                            productList.add(product)
                        }
                    }
                }
            }
        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    fun saveInFirebase(product: Product) {
        val json = gson.toJson(product)
        val body = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$url/products.json")
            .post(body)
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PRICE_WHISPER", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("PRICE_WHISPER", "Product saved!")
            }
        }

        httpClient.newCall(request)
            .enqueue(response)
    }
}