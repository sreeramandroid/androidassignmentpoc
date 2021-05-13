package com.poc.androidassignment.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class LiveDataFragmentTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

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
    fun testVisibilityNoTextLogics() {
        Mockito.`when`(fragLiveData.visibleNodataText())
            .thenThrow(java.lang.RuntimeException::class.java)
        fragLiveData.visibleNodataText()
        assertNotNull(fragLiveData.visibleNodataText())
    }

}