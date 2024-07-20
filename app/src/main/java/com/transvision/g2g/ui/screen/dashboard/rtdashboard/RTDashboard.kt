package com.transvision.g2g.ui.screen.dashboard.rtdashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.transvision.g2g.R
import com.transvision.g2g.ui.common.customeComposibleView.TitleTextBold
import com.transvision.g2g.ui.screen.dashboard.RTI.DataCell
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.custom.getBottomLineShape
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.customDate
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.monthDate
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.quarterWise
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.yearWise
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.zoneWise
import com.transvision.g2g.ui.screen.dashboard.wheelingbanking.headerName
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.columnheaderbg
import com.transvision.g2g.ui.theme.md_theme_dark_background
import com.transvision.g2g.ui.theme.md_theme_light_shadow
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.DrawScrollableView


@Composable
fun RTDashBoard(navController: NavController) {
    val rtViewModel: RTViewModel = hiltViewModel()
    val rtModelState = rtViewModel.uiState.collectAsState()
    var isViewVisible by remember { mutableStateOf(true) }
    var viewDisplay by remember {
        mutableStateOf(RTView.NONE)
    }

    var isQuarterViewVisible by remember { mutableStateOf(false) }

    var rnduiState by remember {
        mutableStateOf(
            RNDUIState("Month Wise", Constants.monthYear, "", "ALL")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.md_theme_light_onTertiary)
            .verticalScroll(rememberScrollState())
    ) {
        CustomToolbarScreen(navController = navController, title = "RT Dashboard", true)

        customDate(rnduiState, onclick = {

            rnduiState = rnduiState.copy(
                customDate = rnduiState.customDate,
                monthYear = if (rnduiState.customDate == "Month Wise") Constants.monthYear else Constants.getCurrentFinancialYear(),
                quarter = if (rnduiState.customDate == "Quarterly Wise") "Quarter1 (Q1)" else ""
            )

            rtViewModel.getRTData(rnduiState)


            isViewVisible = (rnduiState.customDate == "Month Wise")
            isQuarterViewVisible = (rnduiState.customDate == "Quarterly Wise")
        })

        if (isViewVisible) {
            monthDate(rnduiState, onclick = {
                rtViewModel.getRTData(rnduiState)
            })
        }

        if (!isViewVisible)
            yearWise(rnduiState, onclick = {
                rtViewModel.getRTData(rnduiState)
            })
        if (isQuarterViewVisible)
            quarterWise(rnduiState, onclick = {
                rtViewModel.getRTData(rnduiState)
            })
        zoneWise(rnduiState, onclick = {
            rtViewModel.getRTData(rnduiState)
        })
        RTDataDisplay(rtModelState,rtViewModel)
    }
}

@Composable
fun RTDataDisplay(rtModelState: State<RTModelState>,rtViewModel: RTViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        cardRTDataView(
            "Failure Transformers",
            rtModelState.value.rtModel.FailureTransformers ?: "0",
            onclick = {
                rtViewModel.updateRTView(RTView.FailureTransformers)
            }
        )
        cardRTDataView(
            "Condition Monitoring",
            rtModelState.value.rtModel.ConditionMonitoring ?: "0",
            onclick = {
                rtViewModel.updateRTView(RTView.ConditionMonitoring)
            }
        )
        cardRTDataView("Failure Capacitors",
            rtModelState.value.rtModel.FailureCapacitors ?: "0",
           onclick = {
               rtViewModel.updateRTView(RTView.FailureCapacitors)
           }
        )
        cardRTDataView("Total Feeders",
            rtModelState.value.rtModel.TotalFeeders ?: "0",
            onclick = {
                rtViewModel.updateRTView(RTView.TotalFeeders)
            }
        )

        if (rtModelState.value.rtView == RTView.FailureTransformers
            || rtModelState.value.rtView == RTView.FWGP
            || rtModelState.value.rtView == RTView.FAGPScraping
            || rtModelState.value.rtView == RTView.FAGPRepair
            || rtModelState.value.rtView == RTView.FAGPTender)
            failureTransformView(rtModelState.value.rtModel,rtViewModel)
        if (rtModelState.value.rtView == RTView.FWGP
            || rtModelState.value.rtView == RTView.FAGPScraping
            || rtModelState.value.rtView == RTView.FAGPRepair
            || rtModelState.value.rtView == RTView.FAGPTender
            || rtModelState.value.rtView == RTView.ConditionMonitoring)
            tableViewData(rtModelState.value)
        if (rtModelState.value.rtView == RTView.FailureCapacitors)
            failureCapacitorsData(rtModelState.value.rtModel.FailureCapacitorsTableData)
        if (rtModelState.value.rtView == RTView.TotalFeeders){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                cardRTDataView("Existing Feeders",
                    rtModelState.value.rtModel.ExistingFeeders ?: "0",
                    onclick = {

                    }
                )
                cardRTDataView("Metered Feeders",
                    rtModelState.value.rtModel.MeteredFeeders ?: "0",
                    onclick = {

                    }
                )
            }
        }
    }
}

@Composable
fun failureCapacitorsData(failureCapacitorsTableDataList: ArrayList<FailureCapacitorsTableData>) {
    val scrollState = rememberScrollState()
    headerName("FAILURE CAPACITORS")
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(columnheaderbg)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SLNO", 80.dp, md_theme_dark_background)
            DataCell(text = "ZONE", 140.dp, md_theme_dark_background)
            DataCell(text = "CIRCLE", 140.dp, md_theme_dark_background)
            DataCell(text = "DIVISION", 140.dp, md_theme_dark_background)
            DataCell(text = "STATION", 140.dp, md_theme_dark_background)
            DataCell(text = "MAKE", 140.dp, md_theme_dark_background)
            DataCell(text = "CAPACITORS BANK", 140.dp, md_theme_dark_background)
            DataCell(text = "NO OF CELLS", 140.dp, md_theme_dark_background)
            DataCell(text = "CAPACITY CELLS", 140.dp, md_theme_dark_background)
            DataCell(text = "VOLTAGE CELLS", 140.dp, md_theme_dark_background)
            DataCell(text = "FULL OR PARTIAL", 140.dp, md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(300.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (failureCapacitorsTableData in failureCapacitorsTableDataList) {
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
                                failureCapacitorsTableData.SlNo?:"",
                                color = Colors.md_theme_dark_background,
                                width = 80.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.Zone?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.Circle?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.Division?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.STATION?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.MAKE?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.CAPACITORSBANK?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.NOOFCELLS?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.CAPACITYOFCELL?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.VOLTAGEOFCELL?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                            DataCell(
                                text = failureCapacitorsTableData.FULLORPARTIAL?:"",
                                color = Colors.md_theme_dark_background,
                                width = 140.dp
                            )
                        }
                    }
                }

                /*Column {
                    for (item in dssViewModel.state.value.dssModel.CashBalanceTableData){
                        Row(
                            modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                                .border(width = 1.dp,
                                    color = Color.LightGray,
                                    shape = getBottomLineShape(1.dp)
                                )
                                .padding(top = 4.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            DataCell(item.AccountCode?:"0",
                                color = Colors.md_theme_dark_background,
                                width = 80.dp)
                            DataCell(
                                text = item.Description?:"",
                                color = Colors.md_theme_dark_background,
                                width = 230.dp
                            )
                        }
                    }
                }*/
            })
    }
}


@Composable
fun tableViewData(rtModelState: RTModelState) {
    val title = when(rtModelState.rtView){
        RTView.FWGP -> "FWGP FAILURE TRANSFORMERS"
        RTView.FAGPScraping -> "FAGP SCRAPING FAILURE TRANSFORMERS"
        RTView.FAGPRepair -> "FAGP REPAIR FAILURE TRANSFORMERS"
        RTView.FAGPTender -> "FAGP TENDER FAILURE TRANSFORMERS"
        RTView.ConditionMonitoring -> "CONDITION MONITORING"
        else -> {
            ""
        }
    }
    rtModelState.rtModel.FWGPTableData
    val scrollState = rememberScrollState()
    headerName(title)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(columnheaderbg)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SLNO", 80.dp, md_theme_dark_background)
            DataCell(text = "ZONE", 140.dp, md_theme_dark_background)
            DataCell(text = "CIRCLE", 140.dp, md_theme_dark_background)
            DataCell(text = "DIVISION", 140.dp, md_theme_dark_background)
            DataCell(text = "STATION", 140.dp, md_theme_dark_background)
            DataCell(text = "DATE", 140.dp, md_theme_dark_background)
            DataCell(text = "TRANSFORMER NAME", 140.dp, md_theme_dark_background)
            DataCell(text = if (rtModelState.rtView == RTView.ConditionMonitoring)
                                "REMARK" else "REASON",
                                140.dp, md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(300.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    when(rtModelState.rtView){
                        RTView.FWGP -> {
                            for (dataView in rtModelState.rtModel.FWGPTableData){
                                dataBingTable(
                                    slno = dataView.SlNo?:"0",
                                    zone = dataView.Zone?:"0",
                                    circle = dataView.Circle?:"0",
                                    division = dataView.Division?:"",
                                    station = dataView.STATION?:"",
                                    date = dataView.DATE?:"",
                                    transformerName = dataView.TFNAME?:"",
                                    reason = dataView.REASONFAILURE?:""
                                )
                            }
                        }
                        RTView.FAGPScraping -> {
                            for (dataView in rtModelState.rtModel.FAGPScrapingTableData){
                                dataBingTable(
                                    slno = dataView.SlNo?:"0",
                                    zone = dataView.Zone?:"0",
                                    circle = dataView.Circle?:"0",
                                    division = dataView.Division?:"",
                                    station = dataView.STATION?:"",
                                    date = dataView.DATE?:"",
                                    transformerName = dataView.TFNAME?:"",
                                    reason = dataView.REASONFAILURE?:""
                                )
                            }
                        }
                        RTView.FAGPRepair -> {
                            for (dataView in rtModelState.rtModel.FAGPRepairTableData){
                                dataBingTable(
                                    slno = dataView.SlNo?:"0",
                                    zone = dataView.Zone?:"0",
                                    circle = dataView.Circle?:"0",
                                    division = dataView.Division?:"",
                                    station = dataView.STATION?:"",
                                    date = dataView.DATE?:"",
                                    transformerName = dataView.TFNAME?:"",
                                    reason = dataView.REASONFAILURE?:""
                                )
                            }
                        }
                        RTView.FAGPTender -> {
                            for (dataView in rtModelState.rtModel.FAGPTenderTableData){
                                dataBingTable(
                                    slno = dataView.SlNo?:"0",
                                    zone = dataView.Zone?:"0",
                                    circle = dataView.Circle?:"0",
                                    division = dataView.Division?:"",
                                    station = dataView.STATION?:"",
                                    date = dataView.DATE?:"",
                                    transformerName = dataView.TFNAME?:"",
                                    reason = dataView.REASONFAILURE?:""
                                )
                            }
                        }
                        RTView.ConditionMonitoring -> {
                            for (dataView in rtModelState.rtModel.ConditionMonitoringData){
                                dataBingTable(
                                    slno = dataView.SlNo?:"0",
                                    zone = dataView.Zone?:"0",
                                    circle = dataView.Circle?:"0",
                                    division = dataView.Division?:"",
                                    station = dataView.STATION?:"",
                                    date = dataView.DATE?:"",
                                    transformerName = dataView.TFNAME?:"",
                                    reason = dataView.REMARK?:""
                                )
                            }
                        }
                        else -> {

                        }
                    }
                }
            })
    }
}

@Composable
fun dataBingTable(slno: String, zone : String,circle : String,division : String,station : String,
                  date : String,transformerName : String,reason : String){
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
            slno,
            color = Colors.md_theme_dark_background,
            width = 80.dp
        )
        DataCell(
            text = zone,
            color = Colors.md_theme_dark_background,
            width = 140.dp
        )
        DataCell(
            text = circle,
            color = Colors.md_theme_dark_background,
            width = 140.dp
        )
        DataCell(
            text = division,
            color = Colors.md_theme_dark_background,
            width = 140.dp
        )
        DataCell(
            text = station,
            color = Colors.md_theme_dark_background,
            width = 140.dp
        )
        DataCell(
            text = date,
            color = Colors.md_theme_dark_background,
            width = 140.dp
        )
        DataCell(
            text = transformerName,
            color = Colors.md_theme_dark_background,
            width = 140.dp
        )
        DataCell(
            text = reason,
            color = Colors.md_theme_dark_background,
            width = 140.dp
        )
    }
}

@Composable
fun failureTransformView(rtModel: RTModel,rtViewModel: RTViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        cardRTDataView("FWGP",
            rtModel.FWGP ?: "0",
            onclick = {
                rtViewModel.updateRTView(RTView.FWGP)
            }
        )
        cardRTDataView("FAGP Scraping",
            rtModel.FAGPScraping ?: "0",
            onclick = {
                rtViewModel.updateRTView(RTView.FAGPScraping)
            }
        )
        cardRTDataView("FAGP Repair",
            rtModel.FAGPRepair ?: "0",
            onclick = {
                rtViewModel.updateRTView(RTView.FAGPRepair)
            }
        )
        cardRTDataView("FAGP Tender",
            rtModel.FAGPTender ?: "0",
            onclick = {
                rtViewModel.updateRTView(RTView.FAGPTender)
            }
        )

    }
}

@Composable
fun cardRTDataView(title: String, value: String, onclick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier.background(Colors.md_theme_light_onPrimary),
        colors = CardDefaults.cardColors(
            containerColor = Colors.md_theme_light_onPrimary,
        ),
        onClick = {
            onclick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp)
                .padding(vertical = 10.dp)
        ) {
            TitleTextBold(
                modifier = Modifier
                    .padding(top = 2.dp, start = 4.dp)
                    .weight(1F),
                text = title,
                textAlign = TextAlign.Start,
                color = md_theme_light_shadow,
                textUnit = 14.sp,
                font = Font(R.font.poppins_semibold)
            )
            TitleTextBold(
                modifier = Modifier
                    .padding(top = 2.dp, end = 4.dp)
                    .weight(1F),
                text = value,
                textAlign = TextAlign.End,
                color = md_theme_light_shadow,
                textUnit = 14.sp,
                font = Font(R.font.poppins_semibold)
            )
        }
    }
}

enum class RTView {
    FailureTransformers,
    ConditionMonitoring,
    FailureCapacitors,
    TotalFeeders,
    FWGP,
    FAGPScraping,
    FAGPRepair,
    FAGPTender,
    NONE
}