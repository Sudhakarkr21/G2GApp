package com.transvision.g2g.ui.screen.dashboard.RTI

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.transvision.g2g.ui.theme.Colors

@Preview
@Composable
fun rtiViewDataScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.background(Colors.md_theme_dark_background)
                ) {
                    DataCell(text = "TYPES",220.dp)
                    DataCell(text = "APPLICATION BY",180.dp)
                    DataCell(text = "APP_ID")
                    DataCell(text = "REG_NO")
                    DataCell(text = "APPLICANT NAME")
                    DataCell(text = "APPLICANT ADDRESS",200.dp)
                    DataCell(text = "RECEIVE DATE")                }

                Row(
                    modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                ) {
                    DataCell("FAA", color = Colors.md_theme_dark_background, width = 220.dp)
                    DataCell(text = "KPTCL-DGMP-BNG-FAA", color = Colors.md_theme_dark_background, width = 180.dp)
                    DataCell(text = "6", color = Colors.md_theme_dark_background)
                    DataCell(text = "5", color = Colors.md_theme_dark_onTertiary)
                    DataCell(text = "rest", color = Colors.md_theme_dark_onTertiary)
                    DataCell(text = "rest", color = Colors.md_theme_dark_onTertiary)
                    DataCell(text = "01-11-2025", color = Colors.md_theme_dark_onTertiary)
                }

            }

            Spacer(modifier = Modifier.padding(4.dp))
        }
}