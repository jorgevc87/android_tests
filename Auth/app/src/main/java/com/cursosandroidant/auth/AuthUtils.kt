package com.cursosandroidant.auth

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
    if ((email ?: "").isEmpty()) AuthEvent.EMPTY_EMAIL

    if (email == "ant@gmail.com" && password == "1234") AuthEvent.USER_EXISTS

    return AuthEvent.USER_NOT_EXISTS
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email);
}