package com.zj.budgetingapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.zj.budgetingapp.Transaction
import com.zj.budgetingapp.repo.BudgetRepo
import com.zj.budgetingapp.repo.ExchangeRepo
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BudgetViewModel(private val budgetRepo: BudgetRepo, private val exchangeRepo: ExchangeRepo) :
    ViewModel() {

    val exchangeFlow = flow {
        val exchange = exchangeRepo.getExchangeRate("USD", "CNY")
        emit(exchange)
    }

//    val allBudget = budgetRepo.getAll().map { list ->
//        list.map { item ->
//            Transaction(
//                item.name,
//                item.budget.toString(),
//                (item.budget * 6.8f).toString()
//            )
//        }
//    }.asLiveData()

    val allBudget  = budgetRepo.getAll().flatMapMerge { list ->

        exchangeFlow.map { budget ->
            list.map { item ->
                Transaction(
                    item.name,
                    (budget.quotes.USDCNY * item.budget).toString(),
                    item.budget.toString(),
                )
            }
        }

    }.asLiveData()


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