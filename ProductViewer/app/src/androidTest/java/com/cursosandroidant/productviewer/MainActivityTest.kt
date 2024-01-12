package com.cursosandroidant.productviewer

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun setNewQuantity_sum_increasesTextField() {
        onView(withId(R.id.etNewQuantity)).check(ViewAssertions.matches(withText("1")))

        onView(withId(R.id.ibSum)).perform(click())

        onView(withId(R.id.etNewQuantity)).check(ViewAssertions.matches(withText("2")))
    }

    @Test
    fun setNewQuantity_sumLimit_noIncreasesTextField() {

    }

}