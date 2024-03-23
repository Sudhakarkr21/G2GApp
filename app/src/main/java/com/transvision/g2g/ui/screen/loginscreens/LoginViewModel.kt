package com.transvision.g2g.ui.screen.loginscreens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transvision.g2g.di.loginrepository.LoginRepository
import com.transvision.g2g.features.data.model.LoginResponse
import com.transvision.g2g.ui.common.state.ErrorState
import com.transvision.g2g.ui.screen.loginscreens.state.LoginErrorState
import com.transvision.g2g.ui.screen.loginscreens.state.LoginState
import com.transvision.g2g.ui.screen.loginscreens.state.LoginUiEvent
import com.transvision.g2g.ui.screen.loginscreens.state.emailOrMobileEmptyErrorState
import com.transvision.g2g.ui.screen.loginscreens.state.passwordEmptyErrorState
import com.transvision.g2g.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * ViewModel for Login Screen
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    var loginState = mutableStateOf(LoginState())
        private set

    val TAG = "LoginViewModel"

    private val _topHeadlines = MutableLiveData<DataHandler<LoginResponse>>()
    val topHeadlines: LiveData<DataHandler<LoginResponse>> = _topHeadlines

    /**
     * Function called on any login event [LoginUiEvent]
     */
    fun onUiEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {

            // Email/Mobile changed
            is LoginUiEvent.EmailOrMobileChanged -> {
                loginState.value = loginState.value.copy(
                    emailOrMobile = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailOrMobileErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailOrMobileEmptyErrorState
                    )
                )
            }

            // Password changed
            is LoginUiEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    password = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            // Submit Login
            is LoginUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    viewModelScope.launch {
                        val loginResponse = loginRepository.loginResponse(
                            loginState.value.emailOrMobile,
                            loginState.value.password
                        )
                        handleResponse(loginResponse)
                    }
                }
            }
        }
    }

    private fun handleResponse(response: Response<List<LoginResponse>>) {
        Log.d(TAG, "handleResponse: ${response}")
        if (response.isSuccessful) {
            response.body()?.let {
                Log.d(TAG, "handleResponse: ${it.joinToString()}")
                loginState.value = loginState.value.copy(isLoginSuccessful = true)
                return
            }
        }
        loginState.value = loginState.value.copy(isLoginSuccessful = false)
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (usecase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    private fun validateInputs(): Boolean {
        val emailOrMobileString = loginState.value.emailOrMobile.trim()
        val passwordString = loginState.value.password
        return when {

            // Email/Mobile empty
            emailOrMobileString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailOrMobileErrorState = emailOrMobileEmptyErrorState
                    )
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }

}