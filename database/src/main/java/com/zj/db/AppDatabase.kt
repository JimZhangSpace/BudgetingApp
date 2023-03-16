package com.zj.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.zj.db.SeedDatabaseWorker.Companion.KEY_FILENAME
import com.zj.db.dao.BudgetDao
import com.zj.db.dao.UserDao
import com.zj.db.entity.Budget
import com.zj.db.entity.User

const val DATABASE_NAME = "budgets"
const val BUDGET_DATA_FILENAME = "budgets.json"
@Database(entities = [User::class, Budget::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun budgetDao(): BudgetDao

    companion object {

        @Volatile private var instance:AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance?: synchronized(this){
                instance?:buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context):AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object :Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("AppDatabase","onCreate")
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                            .setInputData(workDataOf(
                                KEY_FILENAME to BUDGET_DATA_FILENAME))
                            .build()
                        WorkManager.getInstance(context).enqueue(request)
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        Log.d("AppDatabase","onOpen")
                    }
                })
                .build()
        }
    }
}