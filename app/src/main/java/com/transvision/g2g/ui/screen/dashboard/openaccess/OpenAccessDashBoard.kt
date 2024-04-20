package com.transvision.g2g.ui.screen.dashboard.openaccess

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.transvision.g2g.ui.screen.dashboard.RTI.DataCell
import com.transvision.g2g.ui.screen.dashboard.RTI.RTIModel
import com.transvision.g2g.ui.screen.dashboard.RTI.RTIUIState
import com.transvision.g2g.ui.screen.dashboard.RTI.pieChart
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.custom.getBottomLineShape
import com.transvision.g2g.ui.screen.dashboard.misdashboard.PieChartData
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.Colors.md_theme_light_onPrimary
import com.transvision.g2g.ui.theme.md_theme_dark_onSurfaceVariant
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.DrawScrollableView
import com.transvision.g2g.utils.MonthPicker
import kotlinx.coroutines.launch

@Composable
fun OpenAccessDashBoard(navController: NavController) {


    val openAccessViewModel : OpenAccessViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_light_onPrimary)
                .verticalScroll(rememberScrollState())
        ) {
            CustomToolbarScreen(navController = navController, title = "Open Access Dashboard", true)

            Spacer(modifier = Modifier.padding(4.dp))

            monthDate {
                scope.launch {
                    openAccessViewModel.getOpenAccessData(it)
                }
            }

            Column(
                modifier = Modifier.border(width = 1.dp, color = Colors.md_theme_light_surface)
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(md_theme_dark_onSurfaceVariant)
                    ) {
                        Text(
                            text = "SCHEDULE DATA", modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 10.dp)

                        )
                    }
                }

                SCHEDULEDATAPieChart(openAccessViewModel)
                SCHEDULEDATAViewDataScreen(openAccessViewModel)
            }


            Column(
                modifier = Modifier.border(width = 1.dp, color = Colors.md_theme_light_surface)
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)

            ) {

                Column(
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(md_theme_dark_onSurfaceVariant)
                    ) {
                        Text(
                            text = "METERED DATA", modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 10.dp)

                        )
                    }
                }
                MeterDataPieChart(openAccessViewModel)
                METEREDDATAViewDataScreen(viewModel = openAccessViewModel)

            }

            /*
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
                                text = "SCHEDULE TYPES DATA", modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 10.dp)

                            )
                        }
                    }

                    SCHEDULETYPESDATADataScreen()*/
        }

        if (openAccessViewModel.state.value.loader)
            CircularProgressIndicator()

    }

}


@Composable
fun SCHEDULEDATAPieChart(viewModel: OpenAccessViewModel){
    val arrayList : ArrayList<PieChartData> = ArrayList()

    for (item in viewModel.state.value.openAccessModel.SchedulePie){
            arrayList.add(
                PieChartData(
                    item.type,(item.counts?:0).toFloat()
                )
            )
    }


    Log.d("PieChart", "zoneWisePieChart: ${arrayList}")
    pieChart(arrayList,"")
}
@Composable
fun MeterDataPieChart(viewModel: OpenAccessViewModel){
    val arrayList : ArrayList<PieChartData> = ArrayList()

    for (item in viewModel.state.value.openAccessModel.MeterPie){
            arrayList.add(
                PieChartData(
                    item.type,(item.counts?:0).toFloat()
                )
            )
    }


    Log.d("PieChart", "zoneWisePieChart: ${arrayList}")
    pieChart(arrayList,"")
}

@Composable
fun SCHEDULEDATAViewDataScreen(openAccessViewModel: OpenAccessViewModel) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(Colors.md_theme_dark_background)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SLNO", 80.dp)
            DataCell(text = "Company Name", 330.dp)
            DataCell(text = "Type")
            DataCell(text = "IMP/EXP")
        }
        DrawScrollableView(modifier = Modifier
            .height(300.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                   for (item in openAccessViewModel.state.value.openAccessModel.ScheduleData){
                    Row(
                        modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                            .border(width = 1.dp,
                                color = Color.LightGray,
                                shape = getBottomLineShape(1.dp))
                            .padding(top = 4.dp, bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                            DataCell(item.cmpId?:"", color = Colors.md_theme_dark_background, width = 80.dp)
                            DataCell(
                                text = item.cmpName?:"",
                                color = Colors.md_theme_dark_background,
                                width = 330.dp
                            )
                            DataCell(
                                text = item.TYPE?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(text = "IMPORT", color = Colors.md_theme_dark_onTertiary)

                        }
                    }
                }
            })
    }
}
@Composable
fun METEREDDATAViewDataScreen(viewModel: OpenAccessViewModel) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .background(Colors.md_theme_dark_background)
                .horizontalScroll(scrollState)
        ) {
            DataCell(text = "SLNO", 80.dp)
            DataCell(text = "Company Name", 330.dp)
            DataCell(text = "Type")
        }

        DrawScrollableView(modifier = Modifier
            .height(300.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {

                    LazyColumn(modifier = Modifier.height(300.dp)){
                        items(viewModel.state.value.openAccessModel.MeterData){
                            Row(
                                modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                                    .border(width = 1.dp,
                                        color = Color.LightGray,
                                        shape = getBottomLineShape(1.dp)
                                    )
                                    .padding(top = 4.dp, bottom = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                DataCell(it.cmpId?:"", color = Colors.md_theme_dark_background, width = 80.dp)
                                DataCell(
                                    text = it.cmpName?:"",
                                    color = Colors.md_theme_dark_background,
                                    width = 330.dp
                                )
                                DataCell(
                                    text = it.TYPE?:"",
                                    color = Colors.md_theme_dark_background
                                )
                            }
                        }
                    }

                }
            })
    }
}

@Composable
fun SCHEDULETYPESDATADataScreen(viewModel: OpenAccessViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        DrawScrollableView(modifier = Modifier
            .height(300.dp)
            .horizontalScroll(rememberScrollState()),
            content = {
                Column {
                    Row(
                        modifier = Modifier.background(Colors.md_theme_dark_background)
                    ) {
                        DataCell(text = "SLNO", 80.dp)
                        DataCell(text = "Company Name", 330.dp)
                        DataCell(text = "Type")
                    }
                    repeat(10) {
                    Row(
                        modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                    ) {

                            DataCell("1", color = Colors.md_theme_dark_background, width = 80.dp)
                            DataCell(
                                text = "",
                                color = Colors.md_theme_dark_background,
                                width = 330.dp
                            )
                            DataCell(
                                text = "",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }
}


@Composable
fun monthDate(onclick: (String) -> Unit) {


    var visible by remember {
        mutableStateOf(false)
    }
    var monthYear by remember {
        mutableStateOf(Constants.monthYear)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Select Month: ")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Colors.md_theme_dark_surfaceVariant))
                .clickable { visible = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = monthYear,
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

                    monthYear = "${Constants.months[month_ - 1]}-$year_"
                    visible = false
                    onclick("$month_-$year_")
                },
                cancelClicked = {
                    visible = false
                }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
    }
}