package br.com.anderson.chagas.barberfinance.model

import br.com.anderson.chagas.barberfinance.app.extension.formatHourRightNow
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import java.math.BigDecimal
import java.util.*

class SaleBrain {

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

        fun getTotal(list : List<Sale>?) : BigDecimal {
            if( list?.size!! > 0){
                return list?.map { it.salePrice }?.reduce(getTotalize())
            }
            return BigDecimal(0.0)
        }

        fun getTotalMoney(sales: List<Sale>?) = getTotalBy(sales, "Dinheiro")

        fun getTotalInstallment(sales: List<Sale>?) = getTotalBy(sales, "Fiado")

        fun getTotalCredidCard(sales: List<Sale>?) = getTotalBy(sales, "Cartão de crédito")

        fun getTotalFernando(sales: List<Sale>?) = getTotalBy(sales, "Fernando")

        fun getTotalJunior(sales: List<Sale>?) = getTotalBy(sales, "Junior")

        private fun getTotalize() = { total: BigDecimal, atual: BigDecimal -> total + atual}

        fun getTotalBy(sales: List<Sale>?, s:String) : BigDecimal {
            val filter = sales?.filter { it.barberName == s || it.paymentMethod == s }
            if (filter?.size!! > 0){
                return filter?.map { it.salePrice }?.reduce(getTotalize())
            }
            return BigDecimal(0.0)
        }
    }
}