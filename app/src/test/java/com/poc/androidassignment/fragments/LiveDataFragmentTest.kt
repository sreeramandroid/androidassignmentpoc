package com.poc.androidassignment.fragments

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.androidassignment.viewmodel.HomeViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class LiveDataFragmentTest{
    var application = Mockito.mock(Application::class.java)

    @Mock
    private var viewModel = HomeViewModel(application)

    @Mock
    private var datarepo = RetrofitDataRepository()

    @get:Rule
    public val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var fragLiveData = LiveDataFragment()

    @Test(expected = RuntimeException::class)
    fun checkTheResponse() {
        Mockito.`when`(fragLiveData.refreshList()).thenThrow(RuntimeException::class.java)
        fragLiveData.refreshList()
    }

    @Test(expected = RuntimeException::class)
    fun testCaseForProgressLogic() {
        Mockito.`when`(fragLiveData.refreshList()).thenThrow(RuntimeException::class.java)
        fragLiveData.refreshList()
    }

    @Test(expected = RuntimeException::class)
    fun testviewModelLogics() {
        Mockito.`when`(viewModel.gerAllUsers()).thenThrow(java.lang.RuntimeException::class.java)
        viewModel.gerAllUsers()
    }


    @Test(expected = RuntimeException::class)
    fun testVisibilityNoTextLogics() {
        Mockito.`when`(fragLiveData.visibleNodataText())
            .thenThrow(java.lang.RuntimeException::class.java)
        fragLiveData.visibleNodataText()
    }


    @Test(expected = ExceptionInInitializerError::class)
    fun testForList() {
        Mockito.`when`(viewModel.makeApiCall()).thenThrow(ExceptionInInitializerError::class.java)
        viewModel.makeApiCall()
    }







}