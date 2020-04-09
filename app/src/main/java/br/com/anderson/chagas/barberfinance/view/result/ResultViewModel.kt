package br.com.anderson.chagas.barberfinance.view.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import br.com.anderson.chagas.barberfinance.model.GenerateSale
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import java.util.*

class ResultViewModel(private val saleRepository: SaleRepository) : ViewModel(){

    private val total = MediatorLiveData<String>()
    private val totalMoney = MediatorLiveData<String>()
    private val totalInstallment = MediatorLiveData<String>()
    private val totalCredidCard = MediatorLiveData<String>()
    private val totalFernando = MediatorLiveData<String>()
    private val totalJunior = MediatorLiveData<String>()

    private val creationDate : String
        get() {
            val rightNow = Date()
            return rightNow.formatsDateForBrazilian()
        }

    fun getTotal() : LiveData<String> {
        total.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            total.postValue(GenerateSale.getTotal(sales))
        }
        return total
    }

    fun getTotalMoney() : LiveData<String> {
        totalMoney.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalMoney.postValue(GenerateSale.getTotalMoney(sales))
        }
        return totalMoney
    }

    fun getTotalInstallment() : LiveData<String> {
        totalInstallment.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalInstallment.postValue(GenerateSale.getTotalInstallment(sales))
        }
        return totalInstallment
    }

    fun getTotalCredidCard() : LiveData<String> {
        totalCredidCard.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalCredidCard.postValue(GenerateSale.getTotalCredidCard(sales))
        }
        return totalCredidCard
    }

    fun getTotalFernando() : LiveData<String> {
        totalFernando.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalFernando.postValue(GenerateSale.getTotalFernando(sales))
        }
        return totalFernando
    }

    fun getTotalJunior() : LiveData<String> {
        totalJunior.addSource(saleRepository.getSaleByDate(creationDate)) { sales ->
            totalJunior.postValue(GenerateSale.getTotalJunior(sales))
        }
        return totalJunior
    }

}