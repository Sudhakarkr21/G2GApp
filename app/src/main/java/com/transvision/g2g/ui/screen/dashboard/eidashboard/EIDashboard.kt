package com.transvision.g2g.ui.screen.dashboard.eidashboard

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.transvision.g2g.ui.screen.dashboard.RTI.DataCell
import com.transvision.g2g.ui.screen.dashboard.RTI.pieChart
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.custom.getBottomLineShape
import com.transvision.g2g.ui.screen.dashboard.misdashboard.PieChartData
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.RCModelState
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.monthDate
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.yearWise
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.columnheaderbg1
import com.transvision.g2g.ui.theme.md_theme_dark_background
import com.transvision.g2g.ui.theme.md_theme_dark_onSurfaceVariant
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.DrawScrollableView


@Composable
fun EIDashboard(navController: NavController) {

    val eiViewModel: EIViewModel = hiltViewModel()

    val eiModelState by remember {
        mutableStateOf(
            eiViewModel.state
        )
    }

    var rnduiState by remember {
        mutableStateOf(
            RNDUIState("Month Wise", Constants.monthYear, "", "ALL")
        )
    }
    var isViewVisible by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.md_theme_light_onTertiary)
                .verticalScroll(rememberScrollState())
        ) {

            CustomToolbarScreen(navController = navController, title = "EI DASH BOARD", true)

            customDate(rnduiState, onclick = {

                rnduiState = rnduiState.copy(
                    customDate = rnduiState.customDate,
                    monthYear = if (rnduiState.customDate == "Month Wise") Constants.monthYear else Constants.getCurrentFinancialYear(),
                )
                isViewVisible = (rnduiState.customDate == "Month Wise")

                eiViewModel.getEIDashboard(rnduiState)
            })
            if (isViewVisible) {
                monthDate(rnduiState, onclick = {
                    eiViewModel.getEIDashboard(rnduiState)
                })
            } else {
                yearWise(rnduiState, onclick = {
                    eiViewModel.getEIDashboard(rnduiState)
                })
            }

            if (!eiViewModel.state.value.isloading){
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        modifier = Modifier
                            .weight(1F)
                            .height(100.dp)
                            .background(Colors.md_theme_light_onPrimary),
                        colors = CardDefaults.cardColors(
                            containerColor = Colors.md_theme_light_onPrimary,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Drawing Approval",
                                color = Colors.seed,
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                            Text(
                                text = eiModelState.value.eiModel.DRAWINGAPPROVAL?:"0",
                                color = Colors.md_theme_dark_inverseOnSurface,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        modifier = Modifier
                            .weight(1F)
                            .height(100.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Colors.md_theme_light_onPrimary,
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Demand Note",
                                color = Colors.seed,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                            Text(
                                text = eiModelState.value.eiModel.DEMANDNOTE?:"0",
                                color = Colors.md_theme_dark_inverseOnSurface,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        modifier = Modifier
                            .weight(1F)
                            .height(100.dp)
                            .background(Colors.md_theme_light_onPrimary),
                        colors = CardDefaults.cardColors(
                            containerColor = Colors.md_theme_light_onPrimary,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Spot Inspection",
                                color = Colors.seed,
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                            Text(
                                text = eiModelState.value.eiModel.INSPECTION?:"0",
                                color = Colors.md_theme_dark_inverseOnSurface,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        modifier = Modifier
                            .weight(1F)
                            .height(100.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Colors.md_theme_light_onPrimary,
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Safety Certificate",
                                color = Colors.seed,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                            Text(
                                text = eiModelState.value.eiModel.SAFETYCERTIFICATE?:"0",
                                color = Colors.md_theme_dark_inverseOnSurface,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
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
                            text = "EI WORKS", modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 10.dp)

                        )
                    }
                }
                EIPieChart(eiModelState.value.eiModel)
                overAll(eiModelState.value.eiModel)

                if (eiModelState.value.eiModel.DrawingApprovalTableData.isNotEmpty()){
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
                                text = "Drawing Approval", modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 10.dp)

                            )
                        }
                    }
                    drawingApproval(eiModelState.value.eiModel.DrawingApprovalTableData)
                }

                if (eiModelState.value.eiModel.DemandNoteTableData.isNotEmpty()){
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
                                text = "Demand Note", modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 10.dp)

                            )
                        }
                    }
                    demandNote(eiModelState.value.eiModel.DemandNoteTableData)
                }

                if (eiModelState.value.eiModel.SpotInsepctionTableData.isNotEmpty()){
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
                                text = "Spot Insepction", modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 10.dp)

                            )
                        }
                    }
                    spotInspection(eiModelState.value.eiModel.SpotInsepctionTableData)

                }

                if (eiModelState.value.eiModel.SafetyCertificateTableData.isNotEmpty()){
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
                                text = "Safety Certificate", modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 10.dp)

                            )
                        }
                    }
                    safetyCertificate(eiModelState.value.eiModel.SafetyCertificateTableData)
                }

            }
        }

        if (eiViewModel.state.value.isloading)
            CircularProgressIndicator()
    }

}

@Composable
fun overAll(eiModel: EIModel) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SL No", 80.dp, md_theme_dark_background)
            DataCell(text = "Work Type", 180.dp, md_theme_dark_background)
            DataCell(text = "Count", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(240.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
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
                            "1",
                            color = Colors.md_theme_dark_background,
                            width = 80.dp
                        )
                        DataCell(
                            text = "DRAWINGAPPROVAL",
                            color = Colors.md_theme_dark_background,
                            width = 180.dp
                        )
                        DataCell(
                            text = eiModel.DRAWINGAPPROVAL?:"0",
                            color = Colors.md_theme_dark_background
                        )
                    }
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
                            "1",
                            color = Colors.md_theme_dark_background,
                            width = 80.dp
                        )
                        DataCell(
                            text = "DEMANDNOTE",
                            color = Colors.md_theme_dark_background,
                            width = 180.dp
                        )
                        DataCell(
                            text = eiModel.DEMANDNOTE?:"0",
                            color = Colors.md_theme_dark_background
                        )
                    }
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
                            "1",
                            color = Colors.md_theme_dark_background,
                            width = 80.dp
                        )
                        DataCell(
                            text = "INSPECTION",
                            color = Colors.md_theme_dark_background,
                            width = 180.dp
                        )
                        DataCell(
                            text = eiModel.INSPECTION?:"0",
                            color = Colors.md_theme_dark_background
                        )
                    }
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
                            "1",
                            color = Colors.md_theme_dark_background,
                            width = 80.dp
                        )
                        DataCell(
                            text = "SAFETYCERTIFICATE",
                            color = Colors.md_theme_dark_background,
                            width = 180.dp
                        )
                        DataCell(
                            text = eiModel.SAFETYCERTIFICATE?:"0",
                            color = Colors.md_theme_dark_background
                        )
                    }
                }
            })
    }

}

@Composable
fun drawingApproval(drawingApprovalTableDataList : List<DrawingApprovalTableData>) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SL No", 80.dp, md_theme_dark_background)
            DataCell(text = "Work Project", 180.dp, md_theme_dark_background)
            DataCell(text = "Submission Date", color = md_theme_dark_background)
            DataCell(text = "From Station", color = md_theme_dark_background)
            DataCell(text = "To Station", color = md_theme_dark_background)
            DataCell(text = "Demand Issue Date", color = md_theme_dark_background)
            DataCell(text = "Days Count", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(240.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (drawingApprovalTableData in drawingApprovalTableDataList){
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
                                drawingApprovalTableData.slno?:"",
                                color = Colors.md_theme_dark_background,
                                width = 80.dp
                            )
                            DataCell(
                                text = drawingApprovalTableData.project?:"",
                                color = Colors.md_theme_dark_background,
                                width = 180.dp
                            )
                            DataCell(
                                text = drawingApprovalTableData.subissiondate?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = drawingApprovalTableData.Fromstation?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = drawingApprovalTableData.tostation?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = drawingApprovalTableData.DEMANDISSUEDATE?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = drawingApprovalTableData.days_count?:"",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }

}

@Composable
fun demandNote(demandNoteTableDataList: List<DemandNoteTableData>) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SL No", 80.dp, md_theme_dark_background)
            DataCell(text = "Project", 180.dp, md_theme_dark_background)
            DataCell(text = "From Station", color = md_theme_dark_background)
            DataCell(text = "To Station", color = md_theme_dark_background)
            DataCell(text = "Demand Issue Date", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(240.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (demandNoteTableData in demandNoteTableDataList) {
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
                                demandNoteTableData.slno?:"",
                                color = Colors.md_theme_dark_background,
                                width = 80.dp
                            )
                            DataCell(
                                text = demandNoteTableData.project?:"",
                                color = Colors.md_theme_dark_background,
                                width = 180.dp
                            )
                            DataCell(
                                text = demandNoteTableData.Fromstation?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = demandNoteTableData.tostation?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = demandNoteTableData.DEMANDISSUEDATE?:"",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }

}

@Composable
fun spotInspection(spotInsepctionTableDataList: List<SpotInsepctionTableData>) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SL No", 80.dp, md_theme_dark_background)
            DataCell(text = "Project", 180.dp, md_theme_dark_background)
            DataCell(text = "Issue Date", color = md_theme_dark_background)
            DataCell(text = "Re-Inspection Date", color = md_theme_dark_background)
            DataCell(text = "Days Count", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(240.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (spotInsepctionTableData in spotInsepctionTableDataList) {
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
                                spotInsepctionTableData.slno?:"",
                                color = Colors.md_theme_dark_background,
                                width = 80.dp
                            )
                            DataCell(
                                text = spotInsepctionTableData.project?:"",
                                color = Colors.md_theme_dark_background,
                                width = 180.dp
                            )
                            DataCell(
                                text = spotInsepctionTableData.INSDATE?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = spotInsepctionTableData.REINSPECTIONDATE?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = spotInsepctionTableData.days_count?:"",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }

}
@Composable
fun safetyCertificate(safetyCertificateTableDataList: List<SafetyCertificateTableData>) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SL No", 80.dp, md_theme_dark_background)
            DataCell(text = "Project", 180.dp, md_theme_dark_background)
            DataCell(text = "SC Issue Date", color = md_theme_dark_background)
            DataCell(text = "SC No", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(240.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (safetyCertificateTableData in safetyCertificateTableDataList){
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
                                safetyCertificateTableData.slno?:"",
                                color = Colors.md_theme_dark_background,
                                width = 80.dp
                            )
                            DataCell(
                                text = safetyCertificateTableData.project?:"",
                                color = Colors.md_theme_dark_background,
                                width = 180.dp
                            )
                            DataCell(
                                text = safetyCertificateTableData.SCISSUEDATE?:"",
                                color = Colors.md_theme_dark_background
                            )
                            DataCell(
                                text = safetyCertificateTableData.SCNO?:"",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }

}


@Composable
fun EIPieChart(eiModel: EIModel) {
    val arrayList: ArrayList<PieChartData> = ArrayList()


    arrayList.add(
        PieChartData(
            "DRAWINGAPPROVAL",
            eiModel.DRAWINGAPPROVAL?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "DEMANDNOTE",
            eiModel.DEMANDNOTE?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "INSPECTION",
            eiModel.INSPECTION?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "SAFETYCERTIFICATE",
            eiModel.SAFETYCERTIFICATE?.toFloat()
        )
    )


    Log.d("PieChart", "zoneWisePieChart: ${arrayList}")
    pieChart(arrayList, "NUMBER OF APPLICATIONS")
}

@Composable
fun customDate(rnduiState: RNDUIState, onclick: () -> Unit) {


    val items = listOf<String>("Month Wise", "Year Wise")

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Select Year/Month :",
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