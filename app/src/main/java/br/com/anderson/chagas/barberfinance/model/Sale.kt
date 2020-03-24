package br.com.anderson.chagas.barberfinance.model

import java.io.Serializable
import java.math.BigDecimal

class Sale(
    val barberName : String,
    val creationTime : String,
    val salePrice : BigDecimal,
    val paymentMethod: String
) : Serializable