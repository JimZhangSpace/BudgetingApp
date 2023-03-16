package com.zj.budgetingapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zj.budgetingapp.entity.Budget

@Dao
interface BudgetDao {

    @Insert
    fun insertAll(vararg transaction: Budget)

    @Query("SELECT * FROM budget")
    fun getAll(): List<Budget>
}