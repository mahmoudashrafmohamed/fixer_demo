package com.mahmoud_ashraf.currencyconverter.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoud_ashraf.currencyconverter.data.models.RatesResponse
import com.mahmoud_ashraf.currencyconverter.domain.repositories.CurrencyConverterRepository
import com.mahmoud_ashraf.currencyconverter.domain.usecases.fetchCurrencyRates
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CurrencyRatesViewModel(private val currencyConverterRepository: CurrencyConverterRepository) :
    ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _ratesResponseLiveData = MutableLiveData<RatesResponse>()
    val ratesResponseLiveData  : LiveData<RatesResponse>
        get() = _ratesResponseLiveData

    init {
        fetchRates("EUR")
    }

     fun fetchRates(base : String){
        fetchCurrencyRates(base = base, repository = currencyConverterRepository)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    _ratesResponseLiveData.value = data
                },
                { throwable ->
                    throwable.printStackTrace()
                }

            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}