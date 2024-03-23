package com.transvision.g2g.ui.screen.registration.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.transvision.g2g.R
import com.transvision.g2g.ui.common.customeComposibleView.EmailTextField
import com.transvision.g2g.ui.common.customeComposibleView.MobileNumberTextField
import com.transvision.g2g.ui.common.customeComposibleView.NormalButton
import com.transvision.g2g.ui.common.customeComposibleView.PasswordTextField
import com.transvision.g2g.ui.common.customeComposibleView.TextField
import com.transvision.g2g.ui.theme.AppTheme

@Composable
fun RegistrationInputs(
    registrationState: RegistrationState,
    onEmailIdChange: (String) -> Unit,
    onMobileNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onDesignationName: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    // Login Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {

        // Email ID
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = registrationState.emailId,
            onValueChange = onEmailIdChange,
            label = stringResource(id = R.string.registration_email_label),
            isError = registrationState.errorState.emailIdErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.emailIdErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next
        )

        // Mobile Number
        MobileNumberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = registrationState.mobileNumber,
            onValueChange = onMobileNumberChange,
            label = stringResource(id = R.string.registration_mobile_label),
            isError = registrationState.errorState.mobileNumberErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.mobileNumberErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = registrationState.designationName,
            onValueChange = onDesignationName,
            label = stringResource(id = R.string.registration_designation_label),
            isError = registrationState.errorState.mobileNumberErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.mobileNumberErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next
        )

        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = registrationState.password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.registration_password_label),
            isError = registrationState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.passwordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next
        )

        // Confirm Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = registrationState.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = stringResource(id = R.string.registration_confirm_password_label),
            isError = registrationState.errorState.confirmPasswordErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.confirmPasswordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Done
        )

        // Registration Submit Button
        NormalButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingExtraLarge),
            text = stringResource(id = R.string.registration_button_text),
            onClick = onSubmit
        )


    }
}