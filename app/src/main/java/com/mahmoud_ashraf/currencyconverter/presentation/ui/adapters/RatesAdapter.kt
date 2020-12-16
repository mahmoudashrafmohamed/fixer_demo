package com.mahmoud_ashraf.currencyconverter.presentation.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud_ashraf.currencyconverter.R
import kotlinx.android.synthetic.main.item_view_rate.view.*
import java.lang.Exception

class RatesAdapter(private val itemClick: (Pair<String, Double>, Int) -> Unit) :
    RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    private var list: List<Pair<String, Double>> = mutableListOf()

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(
            R.layout.item_view_rate,
            parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    fun submit(rates: List<Pair<String, Double>>) {
        list = rates
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, private val itemClick: (Pair<String, Double>, Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: Pair<String, Double>, position: Int) {
            with(item) {
                itemView.textViewRateName.text = first
                try {
                    itemView.textViewRateName
                        .setCompoundDrawablesWithIntrinsicBounds(
                            getDrawableByName(itemView.context, first),
                            null, null, null
                        )
                }
                catch (e :Exception){
                    e.printStackTrace()
                }
                itemView.textViewRateValue.text = String.format("%.${4}f", second)
                itemView.setOnClickListener { itemClick(this, position) }
            }
        }

        private fun getDrawableByName(context: Context, nameCurrency: String): Drawable? {
            val packageName = "com.mahmoud_ashraf.currencyconverter"
            val resId = context.resources.getIdentifier("flag_"
                    + nameCurrency.toLowerCase(), "drawable", packageName)
            return if(resId != -1) ContextCompat.getDrawable(context, resId) else null
        }
    }


}