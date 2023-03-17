package com.zj.budgetingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class DemoAdapter(var list:List<Transaction> = emptyList()):RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {

        return LayoutInflater.from(parent.context).inflate(R.layout.adapter_budget, parent, false).let { DemoViewHolder(it) }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class DemoViewHolder(private val view: View):ViewHolder(view){
        fun bind(transaction: Transaction) {

            view.findViewById<TextView>(R.id.name).text = transaction.name
            view.findViewById<TextView>(R.id.cny).text = transaction.cny
            view.findViewById<TextView>(R.id.usd).text = transaction.usd
        }
    }
}