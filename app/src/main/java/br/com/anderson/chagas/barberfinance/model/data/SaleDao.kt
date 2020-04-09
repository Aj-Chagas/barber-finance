package br.com.anderson.chagas.barberfinance.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.anderson.chagas.barberfinance.model.Sale

@Dao
interface SaleDao {

    // 1: Select All
    @Query("SELECT * FROM Sale ORDER BY id DESC")
    fun getAll(): LiveData<List<Sale>>

    @Query("SELECT * FROM Sale WHERE creationDate = :creationDate ORDER BY id DESC")
    fun getByData(creationDate : String) :LiveData<List<Sale>>

    // 2: Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sale: Sale)
}