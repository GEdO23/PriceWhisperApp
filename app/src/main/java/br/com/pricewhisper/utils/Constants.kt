package br.com.pricewhisper.utils

import okhttp3.OkHttpClient

const val RTDB_PRODUCTS_URL = "https://pricewhisper-auth-cc2c8-default-rtdb.firebaseio.com/products.json"
const val PRODUCT_KEY = "product"
val httpClient = OkHttpClient()