package com.transvision.g2g.ui.screen.loginscreens.state

import com.transvision.g2g.ui.common.state.ErrorState

/**
 * Login State holding ui input values
 */
data class LoginState(
    val emailOrMobile: String = "superuser",
    val password: String = "123",
    val errorState: LoginErrorState = LoginErrorState(),
    val isLoginSuccessful: Boolean = false,
    val loading : Boolean = false
)

/**
 * Error state in login holding respective
 * text field validation errors
 */
data class LoginErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)