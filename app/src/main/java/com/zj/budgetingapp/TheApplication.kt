package com.zj.budgetingapp

import android.app.Application
import com.zj.budgetingapp.repo.BudgetRepo
import com.zj.budgetingapp.repo.BudgetRepoImpl
import com.zj.db.AppDatabase

class TheApplication : Application() {
    val database:AppDatabase by lazy { AppDatabase.getInstance(this) }
    val budgetRepo:BudgetRepo by lazy { BudgetRepoImpl(database.budgetDao()) }
}