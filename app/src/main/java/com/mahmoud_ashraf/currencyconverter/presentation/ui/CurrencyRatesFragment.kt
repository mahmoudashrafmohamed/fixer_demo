package com.mahmoud_ashraf.currencyconverter.presentation.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.mahmoud_ashraf.currencyconverter.R
import com.mahmoud_ashraf.currencyconverter.presentation.ui.adapters.RatesAdapter
import com.mahmoud_ashraf.currencyconverter.presentation.viewmodels.CurrencyRatesViewModel
import com.mynameismidori.currencypicker.CurrencyPicker
import kotlinx.android.synthetic.main.fragment_currency_rates.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CurrencyRatesFragment : Fragment() {

    private val viewModel by viewModel<CurrencyRatesViewModel>()
    private val adapter by lazy { RatesAdapter(itemClick = {data,pos->

    }) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        val picker = initPicker()
        picker?.let { initListener(it) }

        viewModel.ratesResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                val list =  it.rates.map {rate->
                    Pair(rate.key,rate.value)
                }
                adapter.submit(list)
            }
            else{
                Toast.makeText(requireContext(),getString(R.string.not_available),Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initView() {
        rv_rates.adapter = adapter
    }

    private fun initPicker(): CurrencyPicker? {
        val picker = CurrencyPicker.newInstance(getString(R.string.base_currency))
        picker.setListener { _, code, _, _ ->
            textViewRateName.text = code
            textViewRateName.setCompoundDrawablesWithIntrinsicBounds(
                getDrawableByName(
                    requireActivity(),
                    code
                ), null, null, null
            )
            viewModel.fetchRates(code)
            picker.dismiss()
        }
        return picker
    }

    private fun getDrawableByName(context: Context, nameCurrency: String): Drawable? {
        val packageName = context.packageName
        val resId = context.resources.getIdentifier("flag_"
                + nameCurrency.toLowerCase(), "drawable", packageName)
        return if(resId != -1) ContextCompat.getDrawable(context, resId) else null
    }

    private fun initListener(picker: CurrencyPicker) {
        btn_change_base.setOnClickListener {
            picker.show(requireActivity().supportFragmentManager, "CURRENCY_PICKER")
        }
    }

}