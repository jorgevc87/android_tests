package com.cursosandroidant.auth

import com.cursosandroidant.auth.exception.AuthException
import org.junit.Assert
import org.junit.Test
import java.lang.NullPointerException

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

    @Test
    fun login_emptyPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "")
        Assert.assertEquals(AuthEvent.EMPTY_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "")
        Assert.assertEquals(AuthEvent.EMPTY_FORM, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmailcom", "")
        Assert.assertEquals(AuthEvent.INVALID_EMAIL, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "123E")
        Assert.assertEquals(AuthEvent.INVALID_PASSWORD, isAuthenticated)
    }

    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException() {
        val isAuthenticated = userAuthenticationTDD(null, "123E")
        Assert.assertEquals(AuthEvent.NULL_EMAIL, isAuthenticated)
    }

    @Test
    fun login_nullPassword_returnsException() {
        Assert.assertThrows(AuthException::class.java) {
            print((userAuthenticationTDD("ant@gmail.com", null)))
        }
    }

    @Test
    fun login_nullForm_returnsException() {
        try {
            val result = userAuthenticationTDD(null, null)
            Assert.assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                Assert.assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent() {
        try {
            val result = userAuthenticationTDD("srgergerg", "123")
            Assert.assertEquals(AuthEvent.LENGHT_PASSWORD, result)
        } catch (e: AuthException) {
            e.let {
                Assert.assertEquals(AuthEvent.LENGHT_PASSWORD, it.authEvent)
            }
        }
    }


    /*

    */

}