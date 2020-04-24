package br.com.anderson.chagas.barberfinance.view.sale.listsales

import androidx.lifecycle.*
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import kotlinx.coroutines.launch
import java.util.*

class SaleListViewModel(
    private val saleRepository: SaleRepository
) : ViewModel(){

    private val saleList = MediatorLiveData<List<Sale>>()
    private val creationDate = MutableLiveData<String>()
    private lateinit var listSaleLiveData : LiveData<List<Sale>>

    init {
        setCreationDate(Calendar.getInstance().formatsDateForBrazilian())
        initializeLiveData()
        getSaleByDate()
    }

    private fun initializeLiveData() {
        listSaleLiveData = Transformations.switchMap(creationDate) { date ->
            saleRepository.fetchListSalesByDate(date)
        }
    }

    private fun setCreationDate(calendar: String) {
        creationDate.value = calendar
    }

    fun getSaleList(): LiveData<List<Sale>>{
        return saleList
    }

    private fun getSaleByDate(){
        saleList.addSource(listSaleLiveData) { sales ->
            saleList.postValue(sales)
        }
    }

    fun removeSale(sale: Sale) {
        viewModelScope.launch {
            saleRepository.deleteSale(sale)
        }
    }
}