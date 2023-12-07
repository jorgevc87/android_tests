package com.cursosandroidant.auth.exception

import com.cursosandroidant.auth.AuthEvent

class AuthException(val authEvent: AuthEvent, message: String? = null) : Exception(message)