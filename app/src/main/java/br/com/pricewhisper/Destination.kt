package br.com.pricewhisper

interface Destination {
    val route: String
}

val homeDestination = object : Destination {
    override val route: String = "HOME"
}

val productListDestination = object : Destination {
    override val route: String = "PRODUCT_LIST"
}

val productFormDestination = object : Destination {
    override val route: String = "PRODUCT_FORM"
}