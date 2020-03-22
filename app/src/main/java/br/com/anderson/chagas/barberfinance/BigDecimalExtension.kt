package br.com.anderson.chagas.barberfinance

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatsCurrencyForBrazilian() : String{
    val formatBrazilian= DecimalFormat
        .getCurrencyInstance(
            Locale("pt", "br")
        )
    return formatBrazilian
        .format(this)
        .replace("R$", "R$ ")
        .replace("-R$", "R$ -")
}