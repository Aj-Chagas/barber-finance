package br.com.anderson.chagas.barberfinance.app.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatsDateForBrazilian() : String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}

fun Calendar.formatDateddMMMMyyyy() : String {
    val formatoBrasileiro = "dd MMMM yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}