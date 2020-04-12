package br.com.anderson.chagas.barberfinance.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import br.com.anderson.chagas.barberfinance.view.servicecost.ServiceCostViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ServiceCostViewModelTest  {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ServiceCostViewModel

    @Mock
    lateinit var repository: SaleRepository


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = ServiceCostViewModel(repository)
    }

    @Test
    fun testCheckChipIsSelected(){
        viewModel.barberName = "Fernando"
        val checkChipIsSelected = viewModel.checkChipIsSelected()
        assertEquals(true, checkChipIsSelected)
    }

    @Test
    fun testCheckChipIsUnselected(){
        viewModel.barberName = ""
        val checkChipIsSelected = viewModel.checkChipIsSelected()
        assertEquals(false, checkChipIsSelected)
    }

    @Test
    fun testCheckValueMoneyIsNotEmpty(){
        val salePrice = "10.00"
        val checkValueMoneyIsNotEmpty = viewModel.checkValueMoneyIsNotEmpty(salePrice)
        assertEquals(true, checkValueMoneyIsNotEmpty)
    }

    @Test
    fun testCheckValueMoneyIsEmpty(){
        val salePrice = ""
        val checkValueMoneyIsNotEmpty = viewModel.checkValueMoneyIsNotEmpty(salePrice)
        assertEquals(false, checkValueMoneyIsNotEmpty)
    }

    @Test
    fun testCheckAllFielsIsNotEmpty(){
        viewModel.barberName = "anderson"
        viewModel.paymentMethod = "dinheiro"
        viewModel.creationDate = "10/10/10"
        viewModel.creationTime = "10:10 pm"
        viewModel.salePrice = "25.00"

        val checkAllFieldsIsNotEmpty = viewModel.checkAllFieldsIsNotEmpty()
        assertEquals(true, checkAllFieldsIsNotEmpty)
    }

    @Test
    fun testCheckOneOrMoreFieldsIsEmpty(){
        viewModel.barberName = ""
        viewModel.paymentMethod = "dinheiro"
        viewModel.creationDate = "10/10/10"
        viewModel.creationTime = ""
        viewModel.salePrice = "25.00"

        val checkAllFieldsIsNotEmpty = viewModel.checkAllFieldsIsNotEmpty()
        assertEquals(false, checkAllFieldsIsNotEmpty)
    }

}