package br.com.anderson.chagas.barberfinance.app

import android.app.Application
import br.com.anderson.chagas.barberfinance.app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BarberFinanceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BarberFinanceApp)
            modules(appModules)
        }
    }
}