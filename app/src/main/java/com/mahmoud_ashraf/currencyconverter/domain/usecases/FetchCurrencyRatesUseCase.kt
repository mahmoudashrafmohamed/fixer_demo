package com.mahmoud_ashraf.currencyconverter.domain.usecases

import com.mahmoud_ashraf.currencyconverter.data.models.RatesResponse
import com.mahmoud_ashraf.currencyconverter.domain.repositories.CurrencyConverterRepository
import io.reactivex.Single

fun fetchCurrencyRates(
    base: String,
    repository: CurrencyConverterRepository
): Single<RatesResponse> {
    return repository.getRates(base)
}