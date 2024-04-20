package com.transvision.g2g.ui.screen.dashboard.rcdashboard

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.transvision.g2g.ui.screen.dashboard.RTI.DataCell
import com.transvision.g2g.ui.screen.dashboard.RTI.RTIModel
import com.transvision.g2g.ui.screen.dashboard.RTI.pieChart
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.custom.getBottomLineShape
import com.transvision.g2g.ui.screen.dashboard.dss.DSSViewModel
import com.transvision.g2g.ui.screen.dashboard.misdashboard.PieChartData
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.columnheaderbg
import com.transvision.g2g.ui.theme.columnheaderbg1
import com.transvision.g2g.ui.theme.headerbg
import com.transvision.g2g.ui.theme.md_theme_dark_background
import com.transvision.g2g.ui.theme.md_theme_dark_onSurfaceVariant
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.DrawScrollableView
import com.transvision.g2g.utils.MonthPicker

@Composable
fun RCDashboardScreen(navController: NavController) {

    val rcViewModel: RCViewModel = hiltViewModel()

    val rcModelState by remember {
        rcViewModel.state
    }

    var rnduiState by remember {
        mutableStateOf(
            RNDUIState("Month Wise", Constants.monthYear, "", "ALL")
        )
    }

    var isViewVisible by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.md_theme_light_onTertiary)
            .verticalScroll(rememberScrollState())
    ) {

        CustomToolbarScreen(navController = navController, title = "RAILWAY CLEARANCE", true)

        customDate(rnduiState, onclick = {

            rnduiState = rnduiState.copy(
                customDate = rnduiState.customDate,
                monthYear = if (rnduiState.customDate == "Month Wise") Constants.monthYear else Constants.getCurrentFinancialYear(),
            )
            isViewVisible = (rnduiState.customDate == "Month Wise")

            rcViewModel.getRCDashboard(rnduiState)
        })
        if (isViewVisible) {
            monthDate(rnduiState, onclick = {
                rcViewModel.getRCDashboard(rnduiState)
            })
        } else {
            yearWise(rnduiState, onclick = {
                rcViewModel.getRCDashboard(rnduiState)
            })
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(md_theme_dark_onSurfaceVariant)
            ) {
                Text(
                    text = "RAILWAY CROSSING", modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)

                )
            }
        }

        RCPieChart(rcModelState)
        Column(
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(headerbg)
            ) {
                Text(
                    text = "Railway Crossing", modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)

                )
            }
        }
        railwayCrossingDataScreen(rcModelState)
        Column(
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(headerbg)
            ) {
                Text(
                    text = "Railway Additional Crossing", modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)

                )
            }
        }
        railwayAdditionalCrossingDataScreen(rcModelState)
    }
}


@Composable
fun railwayCrossingDataScreen(rcModelState: RCModelState) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "Crossing List", 180.dp, md_theme_dark_background)
            DataCell(text = "Total Work Load", 110.dp, md_theme_dark_background)
            DataCell(text = "Completed", color = md_theme_dark_background)
            DataCell(text = "Pending", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(240.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (item in rcModelState.rcModel.CrossingDatadetails) {
                        Row(
                            modifier = Modifier
                                .background(Colors.md_theme_light_onSecondary)
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = getBottomLineShape(1.dp)
                                )
                                .padding(top = 4.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            DataCell(
                                item.CROSSINGLISTS ?: "",
                                color = Colors.md_theme_dark_background,
                                width = 180.dp
                            )
                            DataCell(
                                text = item.TOTALWORKCODE ?: "",
                                color = Colors.md_theme_dark_background,
                                width = 110.dp
                            )
                            DataCell(
                                text = item.COMPLETED ?: "",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = item.PENDING ?: "",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }
}

@Composable
fun railwayAdditionalCrossingDataScreen(rcModelState: RCModelState) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "Additional Crossing List", 180.dp, md_theme_dark_background)
            DataCell(text = "Total Work Load", 110.dp, md_theme_dark_background)
            DataCell(text = "Completed", color = md_theme_dark_background)
            DataCell(text = "Pending", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(240.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (item in rcModelState.rcModel.AddCrossingDatadetails) {
                        Row(
                            modifier = Modifier
                                .background(Colors.md_theme_light_onSecondary)
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = getBottomLineShape(1.dp)
                                )
                                .padding(top = 4.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            DataCell(
                                item.ADDTIONALCROSSINGLISTS ?: "",
                                color = Colors.md_theme_dark_background,
                                width = 180.dp
                            )
                            DataCell(
                                text = item.TOTALWORKCODE ?: "",
                                color = Colors.md_theme_dark_background,
                                width = 110.dp
                            )
                            DataCell(
                                text = item.COMPLETED ?: "",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = item.PENDING ?: "",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }
}

@Composable
fun RCPieChart(rcModelState: RCModelState) {
    val arrayList: ArrayList<PieChartData> = ArrayList()


    arrayList.add(
        PieChartData(
            "Railway Crossing", rcModelState.rcModel.Crossing?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "Railway Addtional Crossing",
            rcModelState.rcModel.AddtionalCrossing?.toFloat()
        )
    )


    Log.d("PieChart", "zoneWisePieChart: ${arrayList}")
    pieChart(arrayList, "NUMBER OF APPLICATIONS")
}


@Composable
fun yearWise(rnduiState: RNDUIState, onclick: () -> Unit) {
    val items = Constants.generateFinancialYears(Constants.getCurrentYear - 10, 14)
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)) {
        Text(
            text = "Select Year :",
            color = Colors.textColor,
            fontSize = 16.sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Colors.borderColor))
                .clickable { expanded = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = rnduiState.monthYear,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "arrow"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(text = {
                        Text(text = item)
                    }, onClick = {
                        expanded = false
                        rnduiState.monthYear = item
                        onclick()
                    }
                    )
                }
            }
        }
    }
}

@Composable
fun monthDate(rnduiState: RNDUIState, onclick: () -> Unit) {


    var visible by remember {
        mutableStateOf(false)
    }



    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Select Month: ")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Colors.borderColor))
                .clickable { visible = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = rnduiState.monthYear,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "arrow"
                )
            }

            MonthPicker(
                visible = visible,
                currentMonth = Constants.currentMonth,
                currentYear = Constants.getCurrentYear,
                confirmButtonCLicked = { month_, year_ ->

                    rnduiState.monthYear = "${Constants.months[month_ - 1]}-$year_"
                    visible = false
                    onclick()
                },
                cancelClicked = {
                    visible = false
                }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun customDate(rnduiState: RNDUIState, onclick: () -> Unit) {


    val items = listOf<String>("Month Wise", "Year Wise")

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Custom Date :",
            color = Colors.textColor,
            fontSize = 16.sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Colors.borderColor))
                .clickable { expanded = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = rnduiState.customDate,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "arrow"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(text = {
                        Text(text = item)
                    }, onClick = {
                        expanded = false
                        rnduiState.customDate = item



                        onclick()
                    }
                    )
                }
            }
        }
    }
}