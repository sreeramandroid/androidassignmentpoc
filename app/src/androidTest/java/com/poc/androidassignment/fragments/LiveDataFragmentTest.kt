package com.poc.androidassignment.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poc.androidassignment.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LiveDataFragmentTest {

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.rvDatalist)).check(matches(isDisplayed()))
    }


    @Test
    fun appSwipeClicked() {
        val scenario = launchFragmentInContainer<LiveDataFragment>()
        onView(withId(R.id.swipeToRefresh)).perform(click())
    }

}