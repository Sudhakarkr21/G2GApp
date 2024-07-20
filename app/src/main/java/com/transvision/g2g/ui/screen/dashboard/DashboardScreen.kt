package com.transvision.g2g.ui.screen.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.transvision.g2g.R
import com.transvision.g2g.ui.common.customeComposibleView.TitleText
import com.transvision.g2g.ui.screen.loginscreens.LoginScreen
import com.transvision.g2g.ui.theme.G2GTheme

import androidx.compose.material3.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.transvision.g2g.ui.screen.dashboard.tabs.ApprovalTab
import com.transvision.g2g.ui.screen.dashboard.tabs.HomeContent
import com.transvision.g2g.ui.screen.dashboard.tabs.SettingsTab
import com.transvision.g2g.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(navController: NavController) {
    val tabs = listOf("DashBoard", "Approve", "Settings")

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = tabs[selectedTab], color = androidx.compose.ui.graphics.Color.White)
                },
                actions = {
                    IconButton(onClick = { /* Handle overflow menu click */ }) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = null, tint = androidx.compose.ui.graphics.Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Colors.textColor1)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                        }
                    )
                }
            }

            // Content for each tab
            when (selectedTab) {
                0 -> HomeContent(navController)
                1 -> ApprovalTab(navController)
                2 -> SettingsTab()
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    G2GTheme {
        DashboardScreen(navController = rememberNavController())
    }
}
