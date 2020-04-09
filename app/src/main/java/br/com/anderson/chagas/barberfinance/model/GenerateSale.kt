package br.com.anderson.chagas.barberfinance.model

import br.com.anderson.chagas.barberfinance.app.extension.formatHourRightNow
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import java.math.BigDecimal
import java.util.*

class GenerateSale {

    companion object {
        fun generateSale(
            barberName: String,
            paymentMethod: String,
            salePrice: String
        ): Sale {

            val rightNow = Date()
            val creationDate = rightNow.formatsDateForBrazilian()
            val creationTime = rightNow.formatHourRightNow()
            val salePrice = salePrice.formatsCurrencyForBrazilian()
            val sale = Sale(barberName = barberName, creationTime = creationTime, creationDate = creationDate, paymentMethod = paymentMethod, salePrice = BigDecimal(salePrice))

            return sale
        }
    }



}