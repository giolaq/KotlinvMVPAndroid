package com.laquysoft.kotlinmvpandroid.ui

import android.app.Fragment
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.laquysoft.kotlinmvpandroid.R
import org.jetbrains.anko.AnkoLogger

/**
 * Created by joaobiriba on 24/09/2017.
 */

class TransactionAdapter(private val context: Context, private val transactionList: MutableList<String>, fragment: Fragment) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>(), AnkoLogger {


    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): TransactionViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_transaction_layout, viewGroup, false)
        return TransactionAdapter.TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder?, position: Int) {
        var transaction = transactionList[position]
        holder!!.itemTitle!!.setText(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class TransactionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        @BindView(R.id.itemTitle)
        @JvmField var itemTitle: TextView? = null
       init {
           ButterKnife.bind(this, itemView)
        }

    }

}