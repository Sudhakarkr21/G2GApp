package com.transvision.g2g.ui.screen.dashboard.wheelingbanking

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NetworkCell
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.transvision.g2g.ui.screen.dashboard.RTI.DataCell
import com.transvision.g2g.ui.screen.dashboard.custom.LineChartCompose
import com.transvision.g2g.ui.screen.dashboard.custom.getBottomLineShape
import com.transvision.g2g.ui.screen.dashboard.dss.DSSViewModel
import com.transvision.g2g.ui.screen.dashboard.eidashboard.EIViewModel
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.monthDate
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.Colors.md_theme_light_onPrimary
import com.transvision.g2g.ui.theme.columnheaderbg
import com.transvision.g2g.ui.theme.columnheaderbg1
import com.transvision.g2g.ui.theme.columnheaderbg2
import com.transvision.g2g.ui.theme.md_theme_dark_background
import com.transvision.g2g.ui.theme.md_theme_dark_onSurfaceVariant
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.DrawScrollableView
import com.transvision.g2g.utils.MonthPicker

@Preview
@Composable
fun wheelingBankingDashBoard() {
    val wheelingBankingViewModel: WheelingBankingViewModel = hiltViewModel()

    val wheelingBankingState = wheelingBankingViewModel.uiState.collectAsState()



    var rnduiState by remember {
        mutableStateOf(
            RNDUIState("Month Wise", Constants.monthYear, "", "ALL")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.md_theme_light_onPrimary)
            .verticalScroll(rememberScrollState())
    ) {
        monthDate(rnduiState, onclick = {
            wheelingBankingViewModel.getWheelingAndBanking(rnduiState)
        })

        Spacer(modifier = Modifier.padding(4.dp))
        cardData(
            "W&B", Icons.Filled.Person,
                if (wheelingBankingState.value.wheelingBankingModel.WBDataExstingIPP.isNotEmpty())
                    wheelingBankingState.value.wheelingBankingModel.WBDataExstingIPP[0].TOTAL?:"0"
                else "0",
            "OLD",
            if (wheelingBankingState.value.wheelingBankingModel.WBDataOld.isNotEmpty())
                wheelingBankingState.value.wheelingBankingModel.WBDataOld[0].TOTALOLD?:"0" else "0",
            "GEOA",
            if (wheelingBankingState.value.wheelingBankingModel.WBDataGeoa.isNotEmpty())
                wheelingBankingState.value.wheelingBankingModel.WBDataGeoa[0].TOTALGEOA?:"0" else "0"
        )
        cardData(
            "C-FORM", Icons.Filled.Menu,
            if (wheelingBankingState.value.wheelingBankingModel.WBDataTOTALCFORM.isNotEmpty())
                wheelingBankingState.value.wheelingBankingModel.WBDataTOTALCFORM[0].TOTALCForm?:"0" else "0",
            "APPROVAL",
            if (wheelingBankingState.value.wheelingBankingModel.WBDataAPPpendCFORM.isNotEmpty())
                wheelingBankingState.value.wheelingBankingModel.WBDataAPPpendCFORM[0].Approved?:"" else "0",
            "PENDING",
            if (wheelingBankingState.value.wheelingBankingModel.WBDataAPPpendCFORM.isNotEmpty())
            wheelingBankingState.value.wheelingBankingModel.WBDataAPPpendCFORM[0].Pending?:"" else "0"
        )
        cardData(
            "B-FORM", Icons.Filled.NetworkCell, "",
            "APPROVAL",
            "11", "PENDING", "12"
        )
        cardData(
            "DCU DATA", Icons.Filled.DocumentScanner, "245", "APPROVAL",
            "11", "PENDING", "12"
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
        ) {


            applicationInfo()
            lineChartApplication()
            barChartWheeling()

        }
    }
}

@Composable
fun bFormTable(){
    val scrollState = rememberScrollState()
    headerName(headerName = "B-FORM")
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(columnheaderbg)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SLNO", 80.dp,md_theme_dark_background)
            DataCell(text = "Description", 230.dp,md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(300.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp)
            ,
            content = {
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
fun headerName(headerName : String){
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(columnheaderbg2)
    ) {
        Text(
            text = headerName, modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp),
            color = Colors.md_theme_light_onPrimary
        )
    }
}

@Composable
fun ColumnScope.lineChartApplication() {
    val entries = listOf(
        Entry(0f, 1f),
        Entry(1f, 2f),
        Entry(2f, 3f),
        Entry(3f, 4f),
        Entry(4f, 3f)
    )

    headerName("MONTH WISE CFORM/BFROM DASHBOARD")
    LineChartCompose(entries)
}

@Composable
fun barChartWheeling() {

    val arrayList = ArrayList<BarEntry>()

    arrayList.add(
        BarEntry(
            1F,
            20F
        )
    )
    arrayList.add(
        BarEntry(
            2F,
            40F)
    )
    arrayList.add(
        BarEntry(
            3F,
            10F)
    )
    arrayList.add(
        BarEntry(
            4F,
            1F)
    )
    arrayList.add(
        BarEntry(
            5F,
            10F)
    )


    headerName(headerName = "APPLICATION DASHBOARD")
    val xLabels = ArrayList<String>()
    xLabels.add("SOLAR")
    xLabels.add("WIND")
    xLabels.add("THERMAL")
    xLabels.add("MINIHYDAL")
    xLabels.add("BIOMASS")
    com.transvision.g2g.ui.screen.dashboard.dss.barChart(
        arrayList = arrayList,
        title = "APPLICATION DASHBOARD",
        xLabels
    )

}

@Composable
fun applicationInfo() {

    val scrollState = rememberScrollState()

    Column {
        headerName(headerName = "APPLICATIONS INFO")
        Row(
            modifier = Modifier
                .background(columnheaderbg1)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "LEVELS", color = md_theme_dark_background)
            DataCell(text = "BESCOM", color = md_theme_dark_background)
            DataCell(text = "GESCOM", color = md_theme_dark_background)
            DataCell(text = "HESCOM", color = md_theme_dark_background)
            DataCell(text = "MESCOM", color = md_theme_dark_background)
            DataCell(text = "CESC", color = md_theme_dark_background)
        }
        Row(
            modifier = Modifier
                .background(md_theme_light_onPrimary)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "SOLAR", color = md_theme_dark_background)
            DataCell(text = "22", color = md_theme_dark_background)
            DataCell(text = "11", color = md_theme_dark_background)
            DataCell(text = "231", color = md_theme_dark_background)
            DataCell(text = "0", color = md_theme_dark_background)
            DataCell(text = "1", color = md_theme_dark_background)
        }
        Row(
            modifier = Modifier
                .background(md_theme_light_onPrimary)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "WIND", color = md_theme_dark_background)
            DataCell(text = "22", color = md_theme_dark_background)
            DataCell(text = "11", color = md_theme_dark_background)
            DataCell(text = "231", color = md_theme_dark_background)
            DataCell(text = "0", color = md_theme_dark_background)
            DataCell(text = "1", color = md_theme_dark_background)
        }
        Row(
            modifier = Modifier
                .background(md_theme_light_onPrimary)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "THERMAL", color = md_theme_dark_background)
            DataCell(text = "22", color = md_theme_dark_background)
            DataCell(text = "11", color = md_theme_dark_background)
            DataCell(text = "231", color = md_theme_dark_background)
            DataCell(text = "0", color = md_theme_dark_background)
            DataCell(text = "1", color = md_theme_dark_background)
        }
        Row(
            modifier = Modifier
                .background(md_theme_light_onPrimary)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "MINIHYDAL", color = md_theme_dark_background)
            DataCell(text = "22", color = md_theme_dark_background)
            DataCell(text = "11", color = md_theme_dark_background)
            DataCell(text = "231", color = md_theme_dark_background)
            DataCell(text = "0", color = md_theme_dark_background)
            DataCell(text = "1", color = md_theme_dark_background)
        }
    }
}

@Composable
fun cardData(
    title: String, imageVector: ImageVector, application: String,
    approvalTextView: String, approvalValueText: String,
    pendingTextView: String, pendingValueText: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            val textTitle = createRef()
            val imgTile = createRef()
            val applicationText = createRef()
            val applicationValue = createRef()
            val approveValue = createRef()
            val pendingValue = createRef()
            val approveText = createRef()
            val pendingText = createRef()
            Text(text = title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(textTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
            Icon(
                imageVector = imageVector,
                contentDescription = "MIS",
                modifier = Modifier.constrainAs(imgTile) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
                tint = Color.Black
            )
            Text(text = application,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .constrainAs(applicationValue) {
                        top.linkTo(imgTile.bottom)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = Colors.md_theme_light_tertiary,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(4.dp)
            )

            Text(text = "Application",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .constrainAs(applicationText) {
                        top.linkTo(textTitle.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(4.dp)
            )
            Text(
                text = approvalValueText,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(approveValue) {
                    top.linkTo(applicationText.bottom)
                    start.linkTo(parent.start)
                },
                fontSize = 18.sp,
            )
            Text(
                text = pendingValueText,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(pendingValue) {
                    top.linkTo(applicationValue.bottom)
                    end.linkTo(parent.end)
                },
                fontSize = 18.sp,
            )
            Text(
                text = approvalTextView,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(approveText) {
                    top.linkTo(approveValue.bottom)
                    end.linkTo(approveValue.end)
                    start.linkTo(approveValue.start)
                },
                fontSize = 18.sp,
            )
            Text(
                text = pendingTextView,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(pendingText) {
                    top.linkTo(pendingValue.bottom)
                    end.linkTo(pendingValue.end)
                    start.linkTo(pendingValue.start)
                },
                fontSize = 18.sp,
            )

            createHorizontalChain(approveValue, pendingValue, chainStyle = ChainStyle.Spread)
        }
    }
}
