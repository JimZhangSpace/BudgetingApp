package com.zj.budgetingapp

import androidx.room.Database
import com.zj.budgetingapp.dao.BudgetDao
import com.zj.budgetingapp.dao.UserDao
import com.zj.budgetingapp.entity.Budget
import com.zj.budgetingapp.entity.User

@Database(entities = [User::class, Budget::class], version = 1)
abstract class AppDatabase {

    abstract fun userDao():UserDao

    abstract fun budgetDao():BudgetDao
}