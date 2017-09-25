package com.laquysoft.kotlinmvpandroid.ui

import android.app.Fragment
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.laquysoft.kotlinmvpandroid.R
import com.laquysoft.kotlinmvpandroid.model.RatesAnswer
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug


/**
 * Created by joaobiriba on 24/09/2017.
 */

class RatesAdapter(private val context: Context, private val currenciesList: MutableList<String>, fragment: Fragment) : RecyclerView.Adapter<RatesAdapter.CurrencyViewHolder>(), AnkoLogger {


    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): CurrencyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false)
        return RatesAdapter.CurrencyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder?, position: Int) {
        var currency = currenciesList[position]
        holder!!.itemTitle!!.setText(currency)
    }

    override fun getItemCount(): Int {
        return currenciesList.size
    }

    class CurrencyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        @BindView(R.id.itemTitle)
        @JvmField var itemTitle: TextView? = null
       init {
            ButterKnife.bind(this, itemView)
        }

    }

    fun clearAndAddElement(currencyNewList: List<String>) {
        currenciesList.clear()
        currenciesList.addAll(currencyNewList)
        notifyDataSetChanged()
    }


}