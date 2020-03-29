package br.com.anderson.chagas.barberfinance.repository

import android.app.Application
import androidx.lifecycle.LiveData
import br.com.anderson.chagas.barberfinance.BarberFinanceApp
import br.com.anderson.chagas.barberfinance.data.SaleDao
import br.com.anderson.chagas.barberfinance.data.SaleDatabase
import br.com.anderson.chagas.barberfinance.model.Sale

class SaleRepository(private val saleDao: SaleDao) {

    fun getAllSale(): LiveData<List<Sale>> {
        return saleDao.getAll()
    }

    fun insertSale(sale: Sale) {
        saleDao.insert(sale)
    }

}
