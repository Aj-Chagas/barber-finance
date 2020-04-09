package br.com.anderson.chagas.barberfinance.view.servicecost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.model.GenerateSale
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class ServiceCostViewModel(private val saleRepository: SaleRepository) : ViewModel() {

    var barberName = ""
    var paymentMethod = ""
    var salePrice = ""

    fun chipSelected(chipSelected : Chip){
        barberName = chipSelected.text.toString()
    }

    fun checkChipIsSelected(): Boolean {
        if(barberName.isNotEmpty()){
            return true
        }
        return false
    }

    fun checkValueMoneyIsNotEmpty(valueMoney: String): Boolean {
        if(valueMoney.trim().isNotEmpty()){
            salePrice = valueMoney
            return true
        }
        return false
    }

    fun saveSale(paymentMethod: String?) {
        this.paymentMethod = paymentMethod ?: ""
            val generateSale = GenerateSale.generateSale(
                barberName,
                this.paymentMethod,
                salePrice
            )
        addSale(generateSale)
    }


    fun checkAllFieldsIsNotEmpty() : Boolean{
        return barberName.isNotEmpty()
                && paymentMethod.isNotEmpty() && salePrice.isNotEmpty()
    }

    private fun addSale(sale:Sale){
        if(checkAllFieldsIsNotEmpty()){
            viewModelScope.launch {
                saleRepository.insertSale(sale)
            }
        }
    }
}