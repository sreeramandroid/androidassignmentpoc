package com.poc.androidassignment.viewmodel

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poc.androidassignment.dbutils.AppDB
import com.poc.androidassignment.dbutils.UserDataSource
import com.poc.androidassignment.model.Row
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest : TestCase() {

    private lateinit var viewModel: HomeViewModel


    @Before()
    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, AppDB::class.java).allowMainThreadQueries()
            .build()
        val dataSource = UserDataSource(db.userDao())
        viewModel = HomeViewModel(Application())
    }

    @Test
    fun testHomeViewModel() {

        var mDataList = ArrayList<Row>()
        var mDataUserList = ArrayList<Row>()
        val userData = Row(
            "description",
            "http://icons.iconarchive.com/icons/iconshock/alaska/256/Igloo-icon.png",
            "title"
        )
        mDataList.add(userData)

        viewModel.addData(mDataList)
        mDataUserList = viewModel.gerAllUsers() as ArrayList<Row>

    }

}