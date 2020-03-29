package br.com.anderson.chagas.barberfinance.model.repository

import androidx.lifecycle.LiveData
import br.com.anderson.chagas.barberfinance.model.data.SaleDao
import br.com.anderson.chagas.barberfinance.model.Sale

class SaleRepository(private val saleDao: SaleDao) {

    fun getAllSale(): LiveData<List<Sale>> {
        return saleDao.getAll()
    }

    fun insertSale(sale: Sale) {
        saleDao.insert(sale)
    }

}
