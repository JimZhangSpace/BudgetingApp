package com.zj.budgetingapp.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.zj.budgetingapp.Transaction
import com.zj.budgetingapp.repo.BudgetRepo
import com.zj.budgetingapp.repo.ExchangeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class BudgetViewModel(private val budgetRepo: BudgetRepo, private val exchangeRepo: ExchangeRepo) :
    ViewModel() {

    val exchangeFlow = flow {
        val exchange = exchangeRepo.getExchangeRate("USD", "CNY")
        emit(exchange)
    }

    val allBudget  = budgetRepo.getAll().flatMapMerge { list ->

        exchangeFlow.map { budget ->
            list.map { item ->
                // TODO: can't get exchange rate
                val cny = budget.quotes?.let { (it.USDCNY * item.budget).toString() } ?: "--"
                Transaction(
                    item.name,
                    cny,
                    item.budget.toString(),
                )
            }
        }.flowOn(Dispatchers.IO)

    }.flowOn(Dispatchers.IO).asLiveData()


}

class BudgetViewModelFactory(
    private val budgetRepo: BudgetRepo,
    private val exchangeRepo: ExchangeRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BudgetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BudgetViewModel(budgetRepo, exchangeRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}