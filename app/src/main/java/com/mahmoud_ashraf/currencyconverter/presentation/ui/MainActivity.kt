package com.mahmoud_ashraf.currencyconverter.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoud_ashraf.currencyconverter.R
import com.mahmoud_ashraf.currencyconverter.presentation.Navigation

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    supportActionBar?.hide()
    initCurrencyFragment()
  }
  private fun initCurrencyFragment() {
    Navigation.replaceFragment(supportFragmentManager, R.id.fragmentContainer, CurrencyRatesFragment())
  }


}