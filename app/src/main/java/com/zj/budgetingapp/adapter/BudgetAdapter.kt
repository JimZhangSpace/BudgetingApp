package com.zj.budgetingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.zj.budgetingapp.BR
import com.zj.budgetingapp.R
import com.zj.budgetingapp.Transaction
import com.zj.budgetingapp.databinding.AdapterBudgetBinding
import com.zj.db.entity.Budget

class BudgetAdapter() : ListAdapter<Transaction, BudgetAdapter.TransactionViewHolder>(
    TRANSACTION_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = DataBindingUtil.inflate<AdapterBudgetBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_budget,
            parent,
            false
        )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TransactionViewHolder(private val binding: AdapterBudgetBinding) : ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.setVariable(BR.transaction, transaction)
            binding.executePendingBindings()
        }
    }

    companion object {
        private val TRANSACTION_COMPARATOR = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem == newItem
            }
        }
    }
}