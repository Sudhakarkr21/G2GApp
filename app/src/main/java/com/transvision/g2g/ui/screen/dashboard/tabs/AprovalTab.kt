package com.transvision.g2g.ui.screen.dashboard.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DesignServices
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.transvision.g2g.ui.screen.NavigationRoutes
import com.transvision.g2g.ui.screen.approva.ApprovalItem
import com.transvision.g2g.ui.screen.dashboard.DashboardScreen
import com.transvision.g2g.ui.theme.G2GTheme

@Composable
fun ApprovalTab(navController: NavController) {

    val item = (1..10).toList()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
    ) {



        LazyColumn{
            items(item) {
                ApprovalItem(onclick = {
                    navController.navigate(NavigationRoutes.Authenticated.Approval.route + "/$it")
                },
                    counnt = it)
            }
        }
    }
    // Text(text = "FavoritesContent")
    // Content for the "Favorites" tab
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    G2GTheme {
        ApprovalTab(navController = rememberNavController())
    }
}
