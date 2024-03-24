package com.transvision.g2g.ui.screen.dashboard.misdashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.accidentScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MISDashBoardScreen(navController: NavController) {

    val tabs = listOf("Question", "Accident")

    var selectedTab by remember { mutableStateOf(0) }


    Scaffold(
        topBar = { CustomToolbarScreen(navController = navController, title = "MIS Dashboard", true) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(innerPadding)
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
                0 -> QuestionScreen()
                1 -> accidentScreen()
            }
        }
    }


}

