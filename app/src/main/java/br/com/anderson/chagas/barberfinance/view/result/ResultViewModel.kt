package br.com.anderson.chagas.barberfinance.view.result

import androidx.lifecycle.*
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import br.com.anderson.chagas.barberfinance.model.SaleBrain
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import java.util.*

class ResultViewModel(private val saleRepository: SaleRepository) : ViewModel(){

    private val total = MediatorLiveData<String>()
    private val totalMoney = MediatorLiveData<String>()
    private val totalInstallment = MediatorLiveData<String>()
    private val totalCredidCard = MediatorLiveData<String>()
    private val totalFernando = MediatorLiveData<String>()
    private val totalJunior = MediatorLiveData<String>()

    private val creationDate = MutableLiveData<String>()

    fun setCreationDate(date: Date) {
        creationDate.value = date.formatsDateForBrazilian()
    }

    fun getTotal() : LiveData<String> {
        total.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            total.postValue(SaleBrain.getTotal(sales))
        }
        return total
    }

    fun getTotalMoney() : LiveData<String> {
        totalMoney.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalMoney.postValue(SaleBrain.getTotalMoney(sales))
        }
        return totalMoney
    }

    fun getTotalInstallment() : LiveData<String> {
        totalInstallment.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalInstallment.postValue(SaleBrain.getTotalInstallment(sales))
        }
        return totalInstallment
    }

    fun getTotalCredidCard() : LiveData<String> {
        totalCredidCard.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalCredidCard.postValue(SaleBrain.getTotalCredidCard(sales))
        }
        return totalCredidCard
    }

    fun getTotalFernando() : LiveData<String> {
        totalFernando.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalFernando.postValue(SaleBrain.getTotalFernando(sales))
        }
        return totalFernando
    }

    fun getTotalJunior() : LiveData<String> {
        totalJunior.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalJunior.postValue(SaleBrain.getTotalJunior(sales))
        }
        return totalJunior
    }



}