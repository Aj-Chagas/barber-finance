package br.com.anderson.chagas.barberfinance.di

import android.app.Application
import androidx.room.Room
import br.com.anderson.chagas.barberfinance.BarberFinanceApp
import br.com.anderson.chagas.barberfinance.data.SaleDatabase
import br.com.anderson.chagas.barberfinance.repository.SaleRepository
import br.com.anderson.chagas.barberfinance.ui.conclusion.ConclusionViewModel
import br.com.anderson.chagas.barberfinance.ui.sale.listsales.SaleListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.definition.Kind
import org.koin.dsl.module


private const val DB_NAME = "Sale.db"


val appModules = module {

    single {
        Room.databaseBuilder(get(), SaleDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<SaleDatabase>().saleDao()
    }

    single {
        SaleRepository(get())
    }

    viewModel {
        SaleListViewModel(get())
    }

    viewModel {
        ConclusionViewModel(get())
    }
}