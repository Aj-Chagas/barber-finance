package br.com.anderson.chagas.barberfinance.view.sale.listsales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import java.util.*

class SaleListViewModel(private val saleRepository: SaleRepository) : ViewModel(){


    private val saleList = MediatorLiveData<List<Sale>>()
    private val creationDate : String
        get() {
            val rightNow = Date()
            return rightNow.formatsDateForBrazilian()
        }

    init {
        getSaleByDate()
    }

    fun getSaleList(): LiveData<List<Sale>>{
        return saleList
    }

    private fun getSaleByDate(){
        saleList.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            saleList.postValue(sales)
        }
    }
}