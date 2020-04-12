package br.com.anderson.chagas.barberfinance.model

import br.com.anderson.chagas.barberfinance.app.extension.formatHourRightNow
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import java.math.BigDecimal
import java.util.*

class SaleBrain {

    companion object {

        private const val MONEY = "Dinheiro"
        private const val INSTALLMENT = "Fiado"
        private const val CREDIT_CARD = "Cartão de crédito"
        private const val FERNANDO = "Fernando"
        private const val JUNIOR = "Junior"

        fun generateSale(
            barberName: String,
            paymentMethod: String,
            salePrice: String
        ): Sale {
            val rightNow = Date()
            return Sale(
                barberName = barberName,
                creationTime = rightNow.formatHourRightNow(),
                creationDate = rightNow.formatsDateForBrazilian(),
                paymentMethod = paymentMethod,
                salePrice = BigDecimal(salePrice.formatsCurrencyForBrazilian()))
        }

        fun getTotal(list : List<Sale>?) : BigDecimal {
            if( list?.size!! > 0){
                return list.map { it.salePrice }.reduce(getTotalize())
            }
            return BigDecimal(0.0)
        }

        fun getTotalMoney(sales: List<Sale>?) = getTotalBy(sales, MONEY)

        fun getTotalInstallment(sales: List<Sale>?) = getTotalBy(sales, INSTALLMENT)

        fun getTotalCredidCard(sales: List<Sale>?) = getTotalBy(sales, CREDIT_CARD)

        fun getTotalFernando(sales: List<Sale>?) = getTotalBy(sales, FERNANDO)

        fun getTotalJunior(sales: List<Sale>?) = getTotalBy(sales, JUNIOR)

        private fun getTotalize() = { total: BigDecimal, atual: BigDecimal -> total + atual}

        private fun getTotalBy(sales: List<Sale>?, s:String) : BigDecimal {
            val filter = sales?.filter { it.barberName == s || it.paymentMethod == s }
            if (filter?.size!! > 0){
                return filter.map { it.salePrice }.reduce(getTotalize())
            }
            return BigDecimal(0.0)
        }
    }
}