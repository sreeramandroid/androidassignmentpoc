package com.poc.androidassignment.viewmodel

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.poc.androidassignment.dbutils.AppDB
import com.poc.androidassignment.dbutils.UserDataSource
import com.poc.androidassignment.model.Row
import com.poc.androidassignment.repository.DatabaseRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : TestCase() {

    private lateinit var viewModel: HomeViewModel


    @Mock
    private lateinit var databaseRepo: DatabaseRepository


    @Before()
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, AppDB::class.java).allowMainThreadQueries()
            .build()
        val dataSource = UserDataSource(db.userDao())
        viewModel = HomeViewModel(Application())
        databaseRepo = DatabaseRepository()
    }


    @Test(expected = RuntimeException::class)
    fun checkTheResponse() {
        Mockito.`when`(viewModel.gerAllUsers()).thenThrow(RuntimeException::class.java)
        viewModel.gerAllUsers()
    }
}