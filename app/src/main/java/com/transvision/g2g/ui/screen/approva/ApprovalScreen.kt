package com.transvision.g2g.ui.screen.approva

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.transvision.g2g.R
import com.transvision.g2g.ui.common.customeComposibleView.SmallClickableWithIconAndText
import com.transvision.g2g.ui.screen.NavigationRoutes
import com.transvision.g2g.ui.screen.registration.state.RegistrationScreen
import com.transvision.g2g.ui.theme.AppTheme
import com.transvision.g2g.ui.theme.G2GTheme

@Composable
fun ApprovalScreen(item : String? = "Empty",navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {

        // Back Button Icon
        SmallClickableWithIconAndText(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.paddingLarge)
                .padding(top = AppTheme.dimens.paddingLarge),
            iconContentDescription = stringResource(id = R.string.navigate_back),
            iconVector = Icons.Outlined.ArrowBack,
            text = stringResource(id = R.string.approval),
            onClick = {
                navController.navigate(NavigationRoutes.Authenticated.Dashboard.route)
            }
        )

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {

            Text(text = "Approval Screen $item")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRegistrationScreen() {
    G2GTheme {
        ApprovalScreen("1", rememberNavController())
    }
}