package com.zj.budgetingapp.repo

import com.zj.db.entity.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepo {

    fun getAll(): Flow<List<Budget>>
}