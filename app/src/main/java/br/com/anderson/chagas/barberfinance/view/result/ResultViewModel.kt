package br.com.anderson.chagas.barberfinance.view.result

import androidx.lifecycle.*
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import br.com.anderson.chagas.barberfinance.model.PieChartBrain
import br.com.anderson.chagas.barberfinance.model.SaleBrain
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import java.math.BigDecimal
import java.util.*

class ResultViewModel(private val saleRepository: SaleRepository) : ViewModel(){

    private val total = MediatorLiveData<BigDecimal>()
    private val totalMoney = MediatorLiveData<BigDecimal>()
    private val totalInstallment = MediatorLiveData<BigDecimal>()
    private val totalCredidCard = MediatorLiveData<BigDecimal>()
    private val totalFernando = MediatorLiveData<BigDecimal>()
    private val totalJunior = MediatorLiveData<BigDecimal>()
    private val creationDate = MutableLiveData<String>()

    init {
        setCreationDate(Calendar.getInstance().formatsDateForBrazilian())
    }

    fun setCreationDate(calendar: String) {
        creationDate.value = calendar
    }

    private val listSaleLiveData = Transformations.switchMap(creationDate) {
        saleRepository.getSaleByDate(it)
    }

    fun getTotal() : LiveData<BigDecimal> {
        total.addSource(listSaleLiveData) { sales ->
            total.postValue(SaleBrain.getTotal(sales))
        }
        return total
    }

    fun getTotalMoney() : LiveData<BigDecimal> {
        totalMoney.addSource(listSaleLiveData) { sales ->
            totalMoney.postValue(SaleBrain.getTotalMoney(sales))
        }
        return totalMoney
    }

    fun getTotalInstallment() : LiveData<BigDecimal> {
        totalInstallment.addSource(listSaleLiveData) { sales ->
            totalInstallment.postValue(SaleBrain.getTotalInstallment(sales))
        }
        return totalInstallment
    }

    fun getTotalCredidCard() : LiveData<BigDecimal> {
        totalCredidCard.addSource(listSaleLiveData) { sales ->
            totalCredidCard.postValue(SaleBrain.getTotalCredidCard(sales))
        }
        return totalCredidCard
    }

    fun getTotalFernando() : LiveData<BigDecimal> {
        totalFernando.addSource(listSaleLiveData) { sales ->
            totalFernando.postValue(SaleBrain.getTotalFernando(sales))
        }
        return totalFernando
    }

    fun getTotalJunior() : LiveData<BigDecimal> {
        totalJunior.addSource(listSaleLiveData) { sales ->
            totalJunior.postValue(SaleBrain.getTotalJunior(sales))
        }
        return totalJunior
    }

    fun calculeteProgressPieChart(value: Double, total: Double): Int {
        return PieChartBrain.calculateProgressPieChart(value, total)
    }

}