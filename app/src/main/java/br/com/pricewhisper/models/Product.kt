package br.com.pricewhisper.models

import java.io.Serializable
import java.math.BigDecimal

data class Product(
    val name: String,
    val price: BigDecimal,
    val stock: UInt = 1u,
    val description: String = ""
): Serializable {
    fun toJson() = """
        {
        "name": "$name",
        "price": "$price",
        "stock": "$stock",
        "description": "$description"
        }
    """.trimIndent()
}
