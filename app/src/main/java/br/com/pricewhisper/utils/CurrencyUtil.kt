package br.com.pricewhisper.utils

import android.icu.text.DecimalFormat
import java.math.BigDecimal

class CurrencyUtil {
    private val priceFormat = DecimalFormat.getCurrencyInstance(appLocale)

    fun format(price: BigDecimal): String = priceFormat.format(price)
    fun format(price: String): BigDecimal = BigDecimal(priceFormat.parse(price).toString())
}