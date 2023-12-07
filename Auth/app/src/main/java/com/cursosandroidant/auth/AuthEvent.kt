package com.cursosandroidant.auth

enum class AuthEvent {

    //success
    USER_EXISTS,

    //fail
    USER_NOT_EXISTS,

    EMPTY_EMAIL,

    EMPTY_PASSWORD,

    EMPTY_FORM,

    INVALID_EMAIL,

    INVALID_PASSWORD,

    //Exceptions
    NULL_EMAIL,

    NULL_PASSWORD,

    NULL_FORM,

    LENGHT_PASSWORD,
}