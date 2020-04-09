package br.com.anderson.chagas.barberfinance.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal

@Entity
data class Sale(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val barberName : String,
    val creationTime : String,
    val creationDate : String,
    val salePrice : BigDecimal,
    val paymentMethod: String
) : Serializable