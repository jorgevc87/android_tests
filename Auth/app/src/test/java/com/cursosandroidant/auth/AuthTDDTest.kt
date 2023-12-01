package com.cursosandroidant.auth

import org.junit.Assert
import org.junit.Test

class AuthTDDTest {

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "1234")
        Assert.assertEquals(AuthEvent.USER_EXISTS, isAuthenticated)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant1@gmail.com", "1234")
        Assert.assertEquals(AuthEvent.USER_EXISTS, isAuthenticated)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "1234")
        Assert.assertEquals(AuthEvent.EMPTY_EMAIL, isAuthenticated)
    }


    /*
    login_emptyPassword_returnsFailEvent
    login_emptyForm_returnsFailEvent
    login_completeForm_invalidEmail_returnsFailEvent
    login_completeForm_invalidPassword_returnsFailEvent
    login_completeForm_invalidUser_returnsFailEvent
    login_nullEmail_returnsException
    login_nullPassword_returnsException
    login_nullForm_returnsException
    login_completeForm_errorLengthPassword_returnsFailEvent
    */


}