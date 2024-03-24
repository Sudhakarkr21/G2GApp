package com.transvision.g2g.ui.screen.loginscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.transvision.g2g.R
import com.transvision.g2g.ui.common.customeComposibleView.MediumTitleText
import com.transvision.g2g.ui.common.customeComposibleView.TitleText
import com.transvision.g2g.ui.screen.loginscreens.state.LoginUiEvent
import com.transvision.g2g.ui.theme.AppTheme
import com.transvision.g2g.ui.theme.G2GTheme


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {

    val loginState by remember {
        loginViewModel.loginState
    }

    if (loginState.isLoginSuccessful) {
        /**
         * Navigate to Authenticated navigation route
         * once login is successful
         */
        LaunchedEffect(key1 = true) {
            onNavigateToAuthenticatedRoute.invoke()
        }
    } else {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Main card Content for Login
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimens.paddingLarge)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = AppTheme.dimens.paddingLarge)
                            .padding(bottom = AppTheme.dimens.paddingExtraLarge)
                    ) {

                        // Heading Jetpack Compose
                        MediumTitleText(
                            modifier = Modifier
                                .padding(top = AppTheme.dimens.paddingLarge)
                                .fillMaxWidth(),
                            text = "G2G",
                            textAlign = TextAlign.Center
                        )

                        // Login Logo
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(128.dp)
                                .padding(top = AppTheme.dimens.paddingSmall),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(data = R.drawable.ic_launcher_foreground)
                                .crossfade(enable = true)
                                .scale(Scale.FILL)
                                .build(),
                            contentDescription = stringResource(id = R.string.login_heading_text)
                        )

                        // Heading Login
                        TitleText(
                            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                            text = stringResource(id = R.string.login_heading_text)
                        )

                        // Login Inputs Composable
                        LoginInputs(
                            loginState = loginState,
                            onEmailOrMobileChange = { inputString ->
                                loginViewModel.onUiEvent(
                                    loginUiEvent = LoginUiEvent.EmailOrMobileChanged(
                                        inputString
                                    )
                                )
                            },
                            onPasswordChange = { inputString ->
                                loginViewModel.onUiEvent(
                                    loginUiEvent = LoginUiEvent.PasswordChanged(
                                        inputString
                                    )
                                )
                            },
                            onSubmit = {
                                loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.Submit)
                            },
                            onForgotPasswordClick = onNavigateToForgotPassword
                        )

                    }
                }

                // Register Section
                Row(
                    modifier = Modifier.padding(AppTheme.dimens.paddingNormal),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Don't have an account?
                    Text(text = stringResource(id = R.string.do_not_have_account))

                    //Register
                    Text(
                        modifier = Modifier
                            .padding(start = AppTheme.dimens.paddingExtraSmall)
                            .clickable {
                                onNavigateToRegistration.invoke()
                            },
                        text = stringResource(id = R.string.register),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            if (loginState.loading)
                CircularProgressIndicator()

        }
        // Full Screen Content


    }

}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    G2GTheme {
        LoginScreen(
            onNavigateToForgotPassword = {},
            onNavigateToRegistration = {},
            onNavigateToAuthenticatedRoute = {}
        )
    }
}