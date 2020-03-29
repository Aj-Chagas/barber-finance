package br.com.anderson.chagas.barberfinance.view.servicecost

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.barberfinance.app.extension.formatHourRightNow
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import com.google.android.material.chip.Chip
import java.math.BigDecimal
import java.util.*

class ServiceCostViewModel(private val saleRepository: SaleRepository) : ViewModel() {

    var chipChecked: Chip? = null
    var salePrice : String? = ""

    fun chipSelected(chipSelected : Chip){
        chipChecked = chipSelected
    }

    fun checkChip(): Boolean {
        if(chipChecked != null){
            return true
        }
        return false
    }

    fun checkValueMoney(valueMoney : EditText): Boolean {
        if(valueMoney.text.trim().isNotEmpty()){
            salePrice = valueMoney.text.toString()
            return true
        }
        return false
    }

    fun saveSale(methodPayment: String?) {
        val rightNow = Date()
        val creationDate = rightNow.formatsDateForBrazilian()
        val creationTime = rightNow.formatHourRightNow()
        val salePrice = salePrice?.formatsCurrencyForBrazilian()

        val sale = Sale(
            barberName = chipChecked!!.text.toString(),
            creationTime = creationTime,
            creationDate = creationDate,
            paymentMethod = methodPayment!!,
            salePrice = BigDecimal(salePrice)
        )
        addSale(sale)
    }

    private fun addSale(sale:Sale){
        saleRepository.insertSale(sale)
    }

}