package br.com.pricewhisper.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import br.com.pricewhisper.models.Product
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException


val httpClient = OkHttpClient()
val gson = Gson()


@Composable
fun FormNewProduct() {
    val name = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val stock = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column {
        Column {
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text("Name")
                }
            )
        }
        Column {
            OutlinedTextField(
                value = price.value,
                onValueChange = { price.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                prefix = {
                    Text("R$")
                },
                label = {
                    Text("Price")
                }
            )
        }
        Column {
            OutlinedTextField(
                value = stock.value,
                onValueChange = { stock.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = {
                    Text("Stock")
                }
            )
        }
        Column {
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text("Description")
                }
            )
        }
        Column {
            Button(onClick = {
                send(
                    Product(
                        name.value,
                        price.value.toBigDecimal(),
                        stock.value.toUInt(),
                        description.value
                    )
                )
            }) {
                Text("Save")
            }
        }
    }
}

fun send(product: Product) {
    val json = gson.toJson(product)

    val body = json.toRequestBody(
        "application/json".toMediaType()
    )

    val request = Request.Builder()
        .url("https://pricewhisper-auth-cc2c8-default-rtdb.firebaseio.com/products.json")
        .post(body)
        .build()

    val response = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("PRICE_WHISPER", e.message.toString())
        }

        override fun onResponse(call: Call, response: Response) {
            Log.i("PRICE_WHISPER", response.message)
        }

    }

    httpClient.newCall(request)
        .enqueue(response)
}
