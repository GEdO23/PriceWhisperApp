package br.com.pricewhisper.utils

import okhttp3.OkHttpClient
import java.util.Locale

const val RTDB_PRODUCTS_URL = "https://pricewhisper-auth-cc2c8-default-rtdb.firebaseio.com/products"
const val PRODUCT_KEY = "product"
const val PRODUCT_ID_KEY = "product_id"
val httpClient = OkHttpClient()
val appLocale = Locale("PT", "BR")