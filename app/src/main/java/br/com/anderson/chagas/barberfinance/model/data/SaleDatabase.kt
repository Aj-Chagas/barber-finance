package br.com.anderson.chagas.barberfinance.model.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.anderson.chagas.barberfinance.model.data.converter.ConverterBigDecimal
import br.com.anderson.chagas.barberfinance.model.Sale

@Database(entities = [Sale::class], version = 2)
@TypeConverters(ConverterBigDecimal::class)
abstract class SaleDatabase : RoomDatabase(){

    abstract fun saleDao() : SaleDao

    companion object{
        private val lock = Any()
        private const val DB_NAME = "Sale.db"
        private var INSTANCE: SaleDatabase? = null

        fun getInstance(application: Application) : SaleDatabase{
            synchronized(lock) {
                if(INSTANCE == null){
                    Room.databaseBuilder(application, SaleDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}