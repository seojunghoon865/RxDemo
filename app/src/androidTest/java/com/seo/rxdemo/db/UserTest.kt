package com.seo.rxdemo.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class UserTest{

    private lateinit var userDao:UserDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun initDb(){
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                AppDatabase::class.java
        ).build()
        userDao = appDatabase.userDao()
    }

    @After
    fun closeDb() = appDatabase.close()

    @Test
    @Throws(Exception::class)
    fun insertTest(){
        val user = User(1, "seo", "junghoon")
        userDao.insert(user);

        val findList = userDao.findAll()

        assertEquals(findList.size,1)
    }

    @Test
    @Throws(Exception::class)
    fun insertArrayTest(){

        val arrayList = arrayListOf<User>(User(1,"seo1","name2"),User(2,"seo2","name2"))
        userDao.insertArray(arrayList)

        val findAll = userDao.findAll()

        assertEquals(findAll.size,2)

    }

    @Test
    @Throws(Exception::class)
    fun findUidTest(){
        val data = User(1,"seo","junghoon")
        userDao.insert(data)
        val findData = userDao.findByuId(1)

        assertEquals(findData.uid,1)
        assertEquals(findData,data)
    }

}