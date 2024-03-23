package com.transvision.g2g.ui.screen.registration.state

/**
 * Registration Screen Events
 */
sealed class RegistrationUiEvent {
    data class EmailChanged(val inputValue: String) : RegistrationUiEvent()
    data class MobileNumberChanged(val inputValue: String) : RegistrationUiEvent()
    data class PasswordChanged(val inputValue: String) : RegistrationUiEvent()
    data class ConfirmPasswordChanged(val inputValue: String) : RegistrationUiEvent()
    data class ConfirmDesignationNameChanged(val inputValue: String) : RegistrationUiEvent()
    object Submit : RegistrationUiEvent()
}