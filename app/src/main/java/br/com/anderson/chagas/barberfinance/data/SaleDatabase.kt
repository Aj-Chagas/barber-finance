package br.com.anderson.chagas.barberfinance.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.anderson.chagas.barberfinance.data.converter.ConverterBigDecimal
import br.com.anderson.chagas.barberfinance.model.Sale

@Database(entities = [Sale::class], version = 1)
@TypeConverters(ConverterBigDecimal::class)
abstract class SaleDatabase : RoomDatabase(){

    abstract fun saleDao() : SaleDao

    companion object{
        private val lock = Any()
        private const val DB_NAME = "Sale.db"
        private var INSTANCE: SaleDatabase? = null

        fun getInstance(application: Application) : SaleDatabase{
            synchronized(SaleDatabase.lock) {
                if(SaleDatabase.INSTANCE == null){
                    Room.databaseBuilder(application, SaleDatabase::class.java, SaleDatabase.DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return SaleDatabase.INSTANCE!!
            }
        }
    }
}