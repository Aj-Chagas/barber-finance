package br.com.anderson.chagas.barberfinance.model.repository

import androidx.lifecycle.LiveData
import br.com.anderson.chagas.barberfinance.model.data.SaleDao
import br.com.anderson.chagas.barberfinance.model.Sale

class SaleRepository(private val saleDao: SaleDao) {

    suspend fun insertSale(sale: Sale) {
        saleDao.insert(sale)
    }

    fun fetchListSalesByDate(creationDate: String): LiveData<List<Sale>> {
        return saleDao.getByData(creationDate)
    }

    fun fetchListByDateRange(dateStart: String, dateFinal: String): LiveData<List<Sale>> {
        return saleDao.getByDate(dateStart, dateFinal)
    }

    suspend fun deleteSale(sale: Sale){
        saleDao.remove(sale)
    }

}
