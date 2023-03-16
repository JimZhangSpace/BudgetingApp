package com.zj.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zj.db.entity.Budget

@Dao
interface BudgetDao {

    @Insert
    fun insertAll(vararg transaction: Budget)

    @Query("SELECT * FROM budget")
    fun getAll(): List<Budget>
}