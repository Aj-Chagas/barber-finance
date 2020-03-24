package br.com.anderson.chagas.barberfinance.ui.sale.listsales

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.barberfinance.BarberFinanceApp
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.repository.SaleRepository

class SaleListViewModel(private val saleRepository: SaleRepository) : ViewModel(){


    private val saleList = MediatorLiveData<List<Sale>>()

    init {
        getAllSale()
    }

    fun getPeopleList(): LiveData<List<Sale>>{
        return saleList
    }

   private fun getAllSale() {
        saleList.addSource(saleRepository.getAllSale()) {sales ->
            saleList.postValue(sales)
        }
    }
}