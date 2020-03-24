package br.com.anderson.chagas.barberfinance.extension

import java.text.SimpleDateFormat
import java.util.*


fun Date.formatsDateForBrazilian() : String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}
