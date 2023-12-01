package com.cursosandroidant.auth

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class AuthTest {

    @Test
    fun login_complete_returnsTrue() {
        val isAuthenticated = userAuthentication("ant@gmail.com", "1234")
        assertTrue(isAuthenticated)
    }

    @Test
    fun login_complete_returnsFalse() {
        val isAuthenticated = userAuthentication("nt@gmail.com", "1234")
        assertFalse(isAuthenticated)
    }

    @Test
    fun login_emptyEmail_returnsFalse() {
        val isAuthenticated = userAuthentication("", "1234")
        assertFalse(isAuthenticated)
    }

    /*  - TDD -

    @Test
    fun login_nullEmail_returnsFalse() {
        val isAuthenticated = userAuthenticationTDD(null, "1234")
        assertFalse(isAuthenticated)
    }

    @Test
    fun login_nullPassword_returnsFalse() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", null)
        assertFalse(isAuthenticated)
    }

    */
}