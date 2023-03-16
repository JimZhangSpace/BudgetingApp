package com.zj.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zj.db.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg users: User)

    @Query("SELECT * FROM users where users.uid = :id")
    fun findUserById(id: Long):User?
    @Query("SELECT * FROM users")
    fun getAll():List<User>
}