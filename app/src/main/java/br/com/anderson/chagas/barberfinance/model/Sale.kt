package br.com.anderson.chagas.barberfinance.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal

@Entity
class Sale(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val barberName : String,
    val creationTime : String,
    val creationDate : String,
    val salePrice : BigDecimal,
    val paymentMethod: String
) : Serializable