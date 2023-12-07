package com.cursosandroidant.auth

import com.cursosandroidant.auth.exception.AuthException

/****
 * Project: Auth
 * From: com.cursosandroidant.auth
 * Created by Alain Nicolás Tello on 14/12/21 at 12:05
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/

fun userAuthentication(email: String, password: String): Boolean {
    if (email == "ant@gmail.com" && password == "1234") {
        return true
    }
    return false
}

fun userAuthenticationTDD(email: String?, password: String?): AuthEvent {

    val isEmailNotEmpty = (email?.length ?: 0) > 0
    val isCorrectPwd = (password?.length ?: 0) != 4

    if (isEmailNotEmpty && isCorrectPwd) {
        throw AuthException(AuthEvent.LENGHT_PASSWORD)
    }

    if (email == null && password == null) throw AuthException(AuthEvent.NULL_FORM)

    if (email == null) throw AuthException(AuthEvent.NULL_PASSWORD)

    if (password == null) throw AuthException(AuthEvent.NULL_EMAIL)

    if ((email ?: "").isEmpty() && (password ?: "").isEmpty()) return AuthEvent.EMPTY_FORM

    if ((email ?: "").isEmpty()) return AuthEvent.EMPTY_EMAIL

    val passwordNumeric = password?.toIntOrNull()

    if ((password ?: "").isNotEmpty() && passwordNumeric == null) return AuthEvent.INVALID_PASSWORD

    if ((email ?: "").isNotEmpty() && !isEmailValid(email ?: "")) return AuthEvent.INVALID_EMAIL

    if (email == "ant@gmail.com" && password == "1234") return AuthEvent.USER_EXISTS

    if (email == "ant@gmail.com" && (password ?: "").isEmpty()) return AuthEvent.EMPTY_PASSWORD

    return AuthEvent.USER_NOT_EXISTS
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email);
}