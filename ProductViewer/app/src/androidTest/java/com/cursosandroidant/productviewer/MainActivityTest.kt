package com.cursosandroidant.productviewer

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.core.AllOf
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
    fun setNewQuantity_subLimit_noIncreasesTextField() {
        //se crea el escenario
        val scenario = activityRule.scenario

        //Se inicia la vista
        scenario.moveToState(Lifecycle.State.RESUMED)

        //Se setea un valor inicial a la cantidad
        scenario.onActivity { activity ->
            activity.selectedProduct.quantity = 1
        }

        //Se valida que el valor inicial de la caja de texto sea el correcto
        onView(withId(R.id.etNewQuantity)).check(ViewAssertions.matches(withText("1")))

        //Se invoca al click
        onView(withId(R.id.ibSub)).perform(click())

        //Se valida que se mantenga el valor
        onView(withId(R.id.etNewQuantity)).check(ViewAssertions.matches(withText("1")))
    }

    @Test
    fun checkTextField_startQuantity() {/*
        Cuando tenemos mas de una vista con el mismo id, entonces podemos hacer referencia
        a el en nuestras pruebas usando la propiedad contentDescription con el metodo:
        withContentDescription
        */
        onView(AllOf.allOf(withId(R.id.etNewQuantity), withContentDescription("cantidad"))).check(
            ViewAssertions.matches(withText("1"))
        )

        onView(
            AllOf.allOf(
                withId(R.id.etNewQuantity),
                withContentDescription("cantidad alterna")
            )
        ).check(ViewAssertions.matches(withText("1")))
    }


}