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

        fun getTotal(list : List<Sale>?) : String {
            val total = list?.map { it.salePrice }?.reduce(getTotalize())
            return total?.formatsCurrencyForBrazilian() ?: "R$ 0,00"
        }

        fun getTotalMoney(sales: List<Sale>?) : String {
            val filter = sales?.filter { it.paymentMethod == "Dinheiro" }
            val total = filter?.map { it.salePrice }?.reduce(getTotalize())
            return total?.formatsCurrencyForBrazilian() ?: "R$ 0,00"
        }

        private fun getTotalize() = { total: BigDecimal, atual: BigDecimal -> total + atual}

        fun getTotalInstallment(sales: List<Sale>?) : String {
            val filter = sales?.filter { it.paymentMethod == "Fiado" }
            val total = filter?.map { it.salePrice }?.reduce(getTotalize())
            return total?.formatsCurrencyForBrazilian() ?: "R$ 0,00"
        }

        fun getTotalCredidCard(sales: List<Sale>?) : String {
            val filter = sales?.filter { it.paymentMethod == "Cartão de crédito" }
            val total = filter?.map { it.salePrice }?.reduce(getTotalize())
            return total?.formatsCurrencyForBrazilian() ?: "R$ 0,00"
        }

        fun getTotalFernando(sales: List<Sale>?) : String {
            val filter = sales?.filter { it.barberName == "Fernando" }
            val total = filter?.map { it.salePrice }?.reduce(getTotalize())
            return total?.formatsCurrencyForBrazilian() ?: "R$ 0,00"
        }

        fun getTotalJunior(sales: List<Sale>?) : String {
            val filter = sales?.filter { it.barberName == "Junior" }
            val total = filter?.map { it.salePrice }?.reduce(getTotalize())
            return total?.formatsCurrencyForBrazilian() ?: "R$ 0,00"
        }
    }
}