package com.zj.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budget(@PrimaryKey val id:Long,
                  @ColumnInfo val name:String,
                  @ColumnInfo val budget:Float)
