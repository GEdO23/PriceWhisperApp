package br.com.pricewhisper.models

import java.math.BigDecimal

data class Product(
    val name: String,
    val price: BigDecimal
)
