package com.transvision.g2g.ui.screen.dashboard.tabs

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.transvision.g2g.ui.screen.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun HomeContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Card(onClick = {
            navController.navigate(NavigationRoutes.Authenticated.MISDashBoard.route)
        },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(6.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Start,
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
                Text(text = "MIS Dashboard")
            }
        }
        Card(onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(6.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Start,
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
                Text(text = "RTI Dashboard")
            }
        }
    }
}