package br.com.pricewhisper.utils

class StockUtil {
    fun format(stock: UInt): String =
        "$stock em estoque"

    fun format(stock: String): UInt =
        stock.replace(" em estoque", "").toUInt()
}