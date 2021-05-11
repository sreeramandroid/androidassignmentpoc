package com.poc.androidassignment.dbutils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.poc.androidassignment.model.Row
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDBTest : TestCase() {

    private lateinit var db: AppDB

    private lateinit var dao: UserDAO

    @Before
    override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDB::class.java).build()
        dao = db.userDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun WriteAndReadData() = runBlocking {

        var mDataList = ArrayList<Row>()
        var mDataUserList = ArrayList<Row>()
        val userData = Row(
            "description",
            "http://icons.iconarchive.com/icons/iconshock/alaska/256/Igloo-icon.png",
            "title"
        )
        mDataList.add(userData)
        dao.saveUserData(mDataList)
        mDataUserList = dao.getAllUserInfo() as ArrayList<Row>
        assertTrue(mDataUserList.contains(mDataList))
        assertThat(mDataUserList.contains(mDataList)).isTrue()
    }
}