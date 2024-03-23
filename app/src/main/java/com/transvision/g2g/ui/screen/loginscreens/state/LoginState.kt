package com.transvision.g2g.ui.screen.loginscreens.state

import com.transvision.g2g.ui.common.state.ErrorState

/**
 * Login State holding ui input values
 */
data class LoginState(
    val emailOrMobile: String = "",
    val password: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
    val isLoginSuccessful: Boolean = false
)

/**
 * Error state in login holding respective
 * text field validation errors
 */
data class LoginErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)