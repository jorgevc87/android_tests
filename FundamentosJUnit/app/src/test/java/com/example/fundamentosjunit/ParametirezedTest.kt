package com.example.fundamentosjunit

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(value = Parameterized::class)
class ParametirezedTest(var currentValue: Boolean, var currentUser: User) {

    @get:Rule
    val locationESRule = LocationESRule()

    companion object {

        var assertions: Assertions? = null

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            assertions = Assertions()
        }

        @AfterClass
        @JvmStatic
        fun tearDownCommon() {
            assertions = null
        }

        @Parameterized.Parameters
        @JvmStatic
        fun getUsersUS() = arrayOf(
            arrayOf(false, User(name = "Pedro", age = 12)),
            arrayOf(true, User(name = "Clara", age = 34)),
            arrayOf(true, User(name = "Bot21", age = 4, isHuman = false)),
            arrayOf(true, User(name = "Alex", age = 18)),
        )
    }

    @Test
    fun isAdult() {
        Assert.assertEquals(currentValue, locationESRule.assertions?.isAdult(currentUser))
    }
}