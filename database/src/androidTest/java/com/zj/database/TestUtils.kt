package com.zj.database

import com.zj.db.entity.Budget
import com.zj.db.entity.User
import kotlin.random.Random

object TestUtils {

    fun createUser(): User {
        val uid = Random.nextLong(0, 1000)
        return User(uid, "13245671123", "haha")
    }

    fun createBudget(): Budget {
        val orderNum = Random.nextLong(100, 1000).toString()
        return Budget(orderNum, 10f)
    }
}