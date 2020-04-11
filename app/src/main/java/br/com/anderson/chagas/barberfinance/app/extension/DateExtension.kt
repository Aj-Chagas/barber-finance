package br.com.anderson.chagas.barberfinance.app.extension

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun Date.formatsDateForBrazilian() : String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}

fun Date.formatHourRightNow() :String{
    val hourFormat: DateFormat = SimpleDateFormat("hh:mm a")
    return hourFormat.format(this)
}

fun Date.formatDateddMMMMyyyy() : String {
    val formatoBrasileiro = "dd MMMM yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}