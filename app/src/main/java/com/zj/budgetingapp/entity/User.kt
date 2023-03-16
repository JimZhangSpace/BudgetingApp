package com.zj.budgetingapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey val uid:Long,
                @ColumnInfo val phoneNum:String,
                @ColumnInfo val nickName:String)
