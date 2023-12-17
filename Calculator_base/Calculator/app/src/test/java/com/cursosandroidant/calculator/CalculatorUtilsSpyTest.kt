package com.cursosandroidant.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsSpyTest {

    @Mock
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup() {
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @Test
    fun calc_callAddPoint_firstPoint_noReturns() {
        val operation = "3x2"
        var isCorrect = false
        calculatorUtils.addPoint(operation) {
            isCorrect = true
        }

        assertTrue(isCorrect)
        Mockito.verifyNoInteractions(operations)
    }
}