package br.com.anderson.chagas.barberfinance.app.di

import androidx.room.Room
import br.com.anderson.chagas.barberfinance.model.data.SaleDatabase
import br.com.anderson.chagas.barberfinance.model.repository.SaleRepository
import br.com.anderson.chagas.barberfinance.view.conclusion.ConclusionViewModel
import br.com.anderson.chagas.barberfinance.view.sale.listsales.SaleListViewModel
import br.com.anderson.chagas.barberfinance.view.servicecost.ServiceCostViewModel
import org.koin.android.viewmodel.dsl.viewModel
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

    viewModel {
        ServiceCostViewModel(get())
    }
}