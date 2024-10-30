package br.com.pricewhisper.models

import java.math.BigDecimal

data class Product(
    var id: String = "",
    var name: String = "",
    var price: BigDecimal = BigDecimal("0.0"),
    var stock: UInt = 1u,
    var description: String = ""
)
