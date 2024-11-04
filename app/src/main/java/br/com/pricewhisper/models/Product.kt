package br.com.pricewhisper.models

import java.math.BigDecimal

/**
 * Data class representing a Product.
 *
 * @property id The unique identifier for the product.
 * @property name The name of the product.
 * @property price The price of the product.
 * @property stock The stock quantity of the product.
 * @property description A brief description of the product.
 */
data class Product(
    var id: String = "",
    var name: String = "",
    var price: BigDecimal = BigDecimal("0.0"),
    var stock: UInt = 1u,
    var description: String = ""
)
