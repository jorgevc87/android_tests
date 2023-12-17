package com.cursosandroidant.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsMockTest {

    //region primer setup de las pruebas
    @Mock
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup() {
        calculatorUtils = CalculatorUtils(operations, listener)
    }
    //endregion

    @Test
    fun cacl_callCheckOnResolve_noReturn() {
        val operation = "-5x2.5"
        val isFromResolve = true
        calculatorUtils.checkOrResolve(operation, isFromResolve)
        Mockito.verify(operations).tryResolve(operation, isFromResolve, listener)
    }

    @Test
    fun calc_callAddOperator_validSub_noReturn() {
        val operator = "-"
        val operation = "4+"
        var isCorrect = false

        calculatorUtils.addOperator(operator, operation) {
            isCorrect = true
        }

        assertTrue(isCorrect)
    }

    @Test
    fun calc_callAddOperator_invalidSub_noReturn() {
        val operator = "-"
        val operation = "4+-"
        var isCorrect = false

        calculatorUtils.addOperator(operator, operation) {
            isCorrect = true
        }

        assertFalse(isCorrect)
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