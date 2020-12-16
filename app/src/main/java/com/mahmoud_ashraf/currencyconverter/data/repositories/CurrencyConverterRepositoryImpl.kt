package com.mahmoud_ashraf.currencyconverter.data.repositories

import com.mahmoud_ashraf.currencyconverter.data.models.RatesResponse
import com.mahmoud_ashraf.currencyconverter.data.remote.FixerApi
import com.mahmoud_ashraf.currencyconverter.domain.repositories.CurrencyConverterRepository
import io.reactivex.Single

class CurrencyConverterRepositoryImpl(private val fixerApi: FixerApi) : CurrencyConverterRepository {
    override fun getRates(base: String): Single<RatesResponse> {
        return fixerApi.getRates(base)
    }
}