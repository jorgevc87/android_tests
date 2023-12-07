package com.cursosandroidant.auth

import com.cursosandroidant.auth.exception.AuthException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Test

class AuthHamcrestTest {

    //Nombre de los tests deacuerdo a estandar en la documentacion de Android
    //given-when-then

    @Test
    fun loginUser_correctData_returnsSuccessEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "1234")
        assertThat(AuthEvent.USER_EXISTS, `is`(isAuthenticated))
    }

    @Test
    fun loginUser_wrongData_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant1@gmail.com", "1234")
        assertThat(AuthEvent.USER_EXISTS, `is`(isAuthenticated))
    }

    @Test
    fun loginUser_emptyEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "1234")
        assertThat(AuthEvent.EMPTY_EMAIL, `is`(isAuthenticated))
    }

    @Test
    fun loginUser_emptyPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "")
        assertThat(AuthEvent.EMPTY_PASSWORD, `is`(isAuthenticated))
    }

    @Test
    fun loginUser_emptyForm_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "")
        assertThat(AuthEvent.EMPTY_FORM, `is`(isAuthenticated))
    }

    @Test
    fun loginUser_invalidaEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmailcom", "")
        assertThat(AuthEvent.INVALID_EMAIL, `is`(isAuthenticated))
    }

    @Test
    fun loginUser_invalidPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "123E")
        assertThat(AuthEvent.INVALID_PASSWORD, `is`(isAuthenticated))
    }

    @Test(expected = AuthException::class)
    fun loginUser_nullEmail_returnsException() {
        val isAuthenticated = userAuthenticationTDD(null, "123E")
        assertThat(AuthEvent.NULL_EMAIL, `is`(isAuthenticated))
    }

    @Test
    fun loginUser_nullPassword_returnsException() {
        Assert.assertThrows(AuthException::class.java) {
            print((userAuthenticationTDD("ant@gmail.com", null)))
        }
    }

    @Test
    fun loginUser_nullForm_returnsException() {
        try {
            val result = userAuthenticationTDD(null, null)
            assertThat(AuthEvent.NULL_FORM, `is`(result))
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                Assert.assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    @Test
    fun loginUser_errorLengthPassword_returnsFailEvent() {
        try {
            val result = userAuthenticationTDD("srgergerg", "123")
            assertThat(AuthEvent.LENGHT_PASSWORD, `is`(result))
        } catch (e: AuthException) {
            e.let {
                assertThat(AuthEvent.LENGHT_PASSWORD, `is`(it.authEvent))
            }
        }
    }

    //Ejercicios con Hamcrest
    @Test
    fun checkNames_differentUsers_match() {
        assertThat("Maria", both(containsString("a")).and(containsString("i")))
    }

    @Test
    fun checkData_emailPassword_noMatch() {
        val email = "ant@gmail.com"
        val password = "1234"
        assertThat(
            email, not(`is`(password))
        ) //Validando con Hamcrest que el correo no es el mismo al password
    }

    @Test
    fun checkExist_newEmail_returnsString() {
        val oldEmail = "ant@gmail.com"
        val newEmail = "ant@cursosandroid.com"

        val emails = arrayOf(oldEmail, newEmail)
        assertThat(emails, hasItemInArray(newEmail))
    }

    @Test
    fun checkDomain_arrayEmail_returnsString() {
        val nextEmail = "alain@cursosandroid.com"
        val oldEmail = "ant@gmail.com"
        val newEmail = "ant@cursosandroid.com"

        val emails = arrayListOf(oldEmail, newEmail, nextEmail)
        val newEmails = arrayListOf(nextEmail, newEmail)

        assertThat(newEmails, everyItem(endsWith("cursosandroid.com")))
    }
}