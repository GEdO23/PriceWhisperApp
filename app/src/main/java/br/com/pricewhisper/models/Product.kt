package br.com.pricewhisper.models

import java.math.BigDecimal

data class Product(
    var name: String,
    var price: BigDecimal,
    var stock: UInt = 1u,
    var description: String = ""
)
