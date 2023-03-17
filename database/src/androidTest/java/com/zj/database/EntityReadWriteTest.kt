package com.zj.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zj.db.AppDatabase
import com.zj.db.dao.BudgetDao
import com.zj.db.dao.UserDao
import com.zj.db.entity.User
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import kotlin.random.Random

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EntityReadWriteTest {

    private lateinit var userDao: UserDao
    private lateinit var budgetDao: BudgetDao
    private lateinit var db:AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = AppDatabase.getInstance(context)
        userDao = db.userDao()
        budgetDao = db.budgetDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

//    @Test
//    @Throws(IOException::class)
//    fun writeUser() {
//        userDao.insertAll(TestUtils.createUser())
//    }
//    @Test
//    @Throws(IOException::class)
//    fun getALLUsers() {
//        userDao.getAll().also(::println)
//    }
//
//    @Test
//    fun findUser() {
//        val user1  = userDao.findUserById(1)
//        assert(user1?.nickName == "haha")
//        val user2  = userDao.findUserById(0)
//        assert(user2!=null)
//    }

    @Test
    @Throws(IOException::class)
    fun writeReadBudget() {
        budgetDao.insertAll(listOf(TestUtils.createBudget()))
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.zj.database.test", appContext.packageName)
    }
}