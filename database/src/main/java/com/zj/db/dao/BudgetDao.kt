package com.zj.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zj.db.entity.Budget
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Insert
    fun insertAll(transaction: List<Budget>)

    @Query("SELECT * FROM budget")
    fun getAll(): Flow<List<Budget>>
}