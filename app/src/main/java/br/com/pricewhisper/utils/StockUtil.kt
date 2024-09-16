package br.com.pricewhisper.utils

import android.content.Context
import br.com.pricewhisper.R

class StockUtil(private val context: Context) {
    fun format(stock: UInt): String =
        "$stock ${context.getString(R.string.in_stock)}"

    fun format(stock: String): UInt =
        stock.replace(" ${context.getString(R.string.in_stock)}", "").toUInt()
}