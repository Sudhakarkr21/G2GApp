package com.transvision.g2g.ui.screen.dashboard.tabs

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.transvision.g2g.ui.activity.RTSDashBoard
import com.transvision.g2g.ui.screen.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun HomeContent(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        cardView("MIS Dashboard", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.MISDashBoard.route)
        })
        cardView("RND Dashboard", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.RNDDashBoard.route)
        })
        cardView("RTI Dashboard", onClick = {
            context.startActivity(
                Intent(
                    context,
                    RTSDashBoard::class.java
                )
            )
        })
        cardView("Vendor Dashboard", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.VendorBoard.route)
        })
        cardView("RTI Dashboard", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.RTIBoard.route)
        })
        cardView("Open Access Dashboard", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.OpenAccessBoard.route)
        })
        cardView("Dission Supportive System", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.DSSBoard.route)
        })
        cardView("RAILWAY CLEARANCE", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.RCBoard.route)
        })
        cardView("EI DASH BOARD", onClick = {
            navController.navigate(NavigationRoutes.Authenticated.EIBoard.route)
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardView(title: String, onClick: () -> Unit) {
    Card(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Icon(
                imageVector = Icons.Filled.Dashboard,
                contentDescription = "MIS"
            )
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Text(text = title)
        }
    }
}