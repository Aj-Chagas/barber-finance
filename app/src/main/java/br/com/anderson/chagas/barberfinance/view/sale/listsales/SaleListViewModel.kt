package br.com.anderson.chagas.barberfinance.view.sale.listsales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository

class SaleListViewModel(private val saleRepository: SaleRepository) : ViewModel(){


    private val saleList = MediatorLiveData<List<Sale>>()

    init {
        getAllSale()
    }

    fun getSaleList(): LiveData<List<Sale>>{
        return saleList
    }

   private fun getAllSale() {
        saleList.addSource(saleRepository.getAllSale()) {sales ->
            saleList.postValue(sales)
        }
    }
}