package com.mahmoud_ashraf.currencyconverter.presentation.di

import com.mahmoud_ashraf.currencyconverter.data.remote.FixerApi
import com.mahmoud_ashraf.currencyconverter.data.repositories.CurrencyConverterRepositoryImpl
import com.mahmoud_ashraf.currencyconverter.domain.repositories.CurrencyConverterRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        provideFetchRatesRepository(
            get()
        )
    }
}

fun provideFetchRatesRepository(fixerApi: FixerApi): CurrencyConverterRepository {
    return CurrencyConverterRepositoryImpl(fixerApi)
}