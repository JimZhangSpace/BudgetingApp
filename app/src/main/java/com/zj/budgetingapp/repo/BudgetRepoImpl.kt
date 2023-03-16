package com.zj.budgetingapp.repo

import com.zj.db.dao.BudgetDao
import com.zj.db.entity.Budget

class BudgetRepoImpl(private val budgetDao: BudgetDao):BudgetRepo {
    override fun getAll() = budgetDao.getAll()
}