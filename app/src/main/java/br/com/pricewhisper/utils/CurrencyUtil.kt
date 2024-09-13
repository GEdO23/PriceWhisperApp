package br.com.pricewhisper.utils

import android.icu.text.DecimalFormat
import java.math.BigDecimal
import java.util.Locale

class CurrencyUtil {
    private val priceFormat = DecimalFormat.getCurrencyInstance(Locale("PT", "BR"))

    fun format(price: BigDecimal): String = priceFormat.format(price)
}