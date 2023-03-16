package com.zj.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@PrimaryKey val uid:Long,
                @ColumnInfo val phoneNum:String,
                @ColumnInfo val nickName:String)
