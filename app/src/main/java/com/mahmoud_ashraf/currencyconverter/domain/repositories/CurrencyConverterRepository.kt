package com.mahmoud_ashraf.currencyconverter.domain.repositories

import com.mahmoud_ashraf.currencyconverter.data.models.RatesResponse
import io.reactivex.Single

interface CurrencyConverterRepository {
    fun getRates(base : String) : Single<RatesResponse>
}