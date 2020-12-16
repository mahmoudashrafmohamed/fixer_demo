package com.mahmoud_ashraf.currencyconverter.presentation.di

import com.mahmoud_ashraf.currencyconverter.presentation.viewmodels.CurrencyRatesViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModule = module {
    viewModel {
        CurrencyRatesViewModel(
            get()
        )
    }

}
