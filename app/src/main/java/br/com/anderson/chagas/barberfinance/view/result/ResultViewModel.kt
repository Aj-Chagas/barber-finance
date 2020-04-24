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
    private val totalCreditCard = MediatorLiveData<BigDecimal>()
    private val totalFernando = MediatorLiveData<BigDecimal>()
    private val totalJunior = MediatorLiveData<BigDecimal>()

    private lateinit var startDate : String
    private lateinit var finalDate : String

    private val updateDataSource = MutableLiveData<Int>()

    init {
        setStartDate(Calendar.getInstance().formatsDateForBrazilian())
        setFinalDate(Calendar.getInstance().formatsDateForBrazilian())
        updateDataSource.value = 1
    }

    fun setStartDate(calendar: String) {
        startDate = calendar
    }

    /**
     * toda vez que o método updateDatSource for executado, será feita uma busca no banco de dados
     * de acordo com a nova data escolhidda
     * */

    fun setUpdateDataSource (){
        updateDataSource.value = 2
    }

    fun setFinalDate(calendar: String){
        finalDate = calendar
    }

    private val listSaleLiveData = Transformations.switchMap(updateDataSource)  {
        saleRepository.fetchListByDateRange(startDate, finalDate)
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
        totalCreditCard.addSource(listSaleLiveData) { sales ->
            totalCreditCard.postValue(SaleBrain.getTotalCredidCard(sales))
        }
        return totalCreditCard
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


    fun isValidDate() : Boolean {
        return startDate <= finalDate
    }

    fun validDateFieldsIsNotEmpty(
    ): Boolean {
        if (startDate.isNotEmpty() && finalDate.isNotEmpty()) {
            return true
        }
        return false
    }



}