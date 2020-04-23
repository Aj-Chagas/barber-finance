package br.com.anderson.chagas.barberfinance.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.anderson.chagas.barberfinance.model.Sale

@Dao
interface SaleDao {

    @Query("SELECT * FROM Sale ORDER BY id DESC")
    fun getAll(): LiveData<List<Sale>>

    @Query("SELECT * FROM Sale WHERE creationDate = :creationDate ORDER BY id DESC")
    fun getByData(creationDate : String) :LiveData<List<Sale>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sale: Sale)

    @Delete
    suspend fun remove(sale: Sale)

    @Query("SELECT * FROM Sale WHERE creationDate BETWEEN :dateStart and :dateFinal ORDER BY id DESC")
    fun getByDate(dateStart : String, dateFinal : String) :LiveData<List<Sale>>
}