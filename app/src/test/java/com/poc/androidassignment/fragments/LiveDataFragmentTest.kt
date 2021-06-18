package com.poc.androidassignment.fragments

import android.app.Application
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.androidassignment.model.Row
import com.poc.androidassignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_livedata.*
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class LiveDataFragmentTest {
    var application = Mockito.mock(Application::class.java)

    @Mock
    private var viewModel = HomeViewModel(application)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var fragLiveData = LiveDataFragment()

    @Test(expected = UninitializedPropertyAccessException::class)
    fun checkTheErrorResponse() {
        Mockito.`when`(fragLiveData.viewModel.countryResponseData.value).thenReturn(null)
        fragLiveData.viewModel.gerAllUsers()
        assert(fragLiveData.txtnodataavail.visibility == View.VISIBLE)
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun checkTheSuccessResponse() {
        var mDataList = ArrayList<Row>()
        val mUserDataSource = Row("description", "http://data.png", "title")
        mDataList.add(mUserDataSource)

        Mockito.`when`(fragLiveData.viewModel.countryResponseData.value).thenReturn(null)
        fragLiveData.viewModel.gerAllUsers()
        assert(fragLiveData.txtnodataavail.visibility == View.VISIBLE)
    }

    @Test(expected = RuntimeException::class)
    fun testCaseForProgressLogic() {
        Mockito.`when`(fragLiveData.refreshList()).thenThrow(RuntimeException::class.java)
        fragLiveData.refreshList()
    }

    @Test(expected = RuntimeException::class)
    fun testVisibilityNoTextLogics() {
        Mockito.`when`(fragLiveData.visibleNodataText())
            .thenThrow(RuntimeException::class.java)
        fragLiveData.visibleNodataText()
        assertNotNull(fragLiveData.visibleNodataText())
    }
}