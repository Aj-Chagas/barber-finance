package br.com.anderson.chagas.barberfinance.app.extension

fun String.formatsCurrencyForBrazilian() : String{

    return this
        .replace("R$", "")
        .replace(",", ".").trim()
}