package com.mahmoud_ashraf.currencyconverter.presentation

import android.app.Application
import com.mahmoud_ashraf.currencyconverter.presentation.di.networkModule
import com.mahmoud_ashraf.currencyconverter.presentation.di.repositoryModule
import com.mahmoud_ashraf.currencyconverter.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            // Koin logger
            androidLogger()
            // inject Android context
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }


}