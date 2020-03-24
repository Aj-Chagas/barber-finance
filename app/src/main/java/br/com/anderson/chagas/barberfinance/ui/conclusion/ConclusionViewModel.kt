package br.com.anderson.chagas.barberfinance.ui.conclusion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.barberfinance.BarberFinanceApp
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.repository.SaleRepository

class ConclusionViewModel(private val saleRepository: SaleRepository) : ViewModel() {

    fun addSale(sale:Sale){
        saleRepository.insertSale(sale)
    }

}