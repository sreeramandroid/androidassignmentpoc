package com.poc.androidassignment.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.poc.androidassignment.dbutils.AppDB
import com.poc.androidassignment.dbutils.UserDataSource
import com.poc.androidassignment.model.Row
import com.poc.androidassignment.repository.DatabaseRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : TestCase() {

    private lateinit var viewModel: HomeViewModel

    // @Mock
    private lateinit var databaseRepo: DatabaseRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    public override fun setUp() {
        super.setUp()
        viewModel = HomeViewModel(Application())
        databaseRepo = DatabaseRepository()
    }

    @Test(expected = RuntimeException::class)
    fun checkTheResponse() {
        Mockito.`when`(viewModel.gerAllUsers()).thenThrow(RuntimeException::class.java)
        viewModel.gerAllUsers()
    }

    @Test(expected = RuntimeException::class)
    fun testNullorEmptyForList() {
        Mockito.`when`(viewModel.gerAllUsers()).thenThrow(RuntimeException::class.java)
        assertNotNull(viewModel.countryResponseData)
        assertFalse(viewModel.countryResponseData.value.isNullOrEmpty())
        assertTrue(viewModel.countryResponseData.hasObservers())
    }

    @Test(expected = ExceptionInInitializerError::class)
    fun testNullForDatabaseRepo() {
        Mockito.`when`(viewModel.makeApiCall()).thenThrow(ExceptionInInitializerError::class.java)
        assertNotNull(viewModel.makeApiCall())
    }
}