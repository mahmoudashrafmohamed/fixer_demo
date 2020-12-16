package com.mahmoud_ashraf.currencyconverter.data.remote

import com.mahmoud_ashraf.currencyconverter.data.models.RatesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FixerApi {
    @GET("latest")
    fun getRates(@Query("base") base : String) : Single<RatesResponse>
}