package com.transvision.g2g.ui.screen.dashboard.RTI

import android.graphics.Typeface
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.custom.getBottomLineShape
import com.transvision.g2g.ui.screen.dashboard.misdashboard.PieChartData
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDUIState
import com.transvision.g2g.ui.screen.dashboard.vendor.NumberofApplications
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.Colors.bgColor
import com.transvision.g2g.ui.theme.Colors.md_theme_dark_background
import com.transvision.g2g.ui.theme.Colors.md_theme_dark_onBackground
import com.transvision.g2g.ui.theme.Colors.md_theme_dark_onTertiary
import com.transvision.g2g.ui.theme.Colors.md_theme_light_onSecondary
import com.transvision.g2g.ui.theme.Colors.md_theme_light_outline
import com.transvision.g2g.ui.theme.md_theme_dark_onSurfaceVariant
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.MonthPicker


@Composable
fun RTIDashBoardScreen(navController: NavController) {

    val rtiViewModel : RTIViewModel = hiltViewModel()

    val rtiModelState  by remember {
        rtiViewModel.state
    }

    var rtiuiState by remember {
       mutableStateOf(RTIUIState("Year Wise",Constants.getCurrentFinancialYear()))
    }

    var isViewVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.md_theme_light_onTertiary)
            .verticalScroll(rememberScrollState())
    ) {

        CustomToolbarScreen(navController = navController, title = "RTI Dashboard", true)

        customDate(rtiuiState){
            if (it == "Year Wise"){
                isViewVisible = true
                rtiuiState.monthYear = Constants.getCurrentFinancialYear()
            } else {
                rtiuiState.monthYear = Constants.monthYear
                isViewVisible = false
            }

            rtiViewModel.getRTIData(rtiuiState)
        }

        if (isViewVisible){
            yearWise(rtiuiState){
                rtiViewModel.getRTIData(rtiuiState)
            }
        }
        if (!isViewVisible){
            monthDate(rnduiState = rtiuiState) {
                rtiViewModel.getRTIData(rtiuiState)
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
                    text = "APPLICATION DETAILS", modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)

                )
            }
        }

        if (rtiModelState.rtiModel.Applicationdetails.isNotEmpty()){
            Column(modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.background(md_theme_dark_background)
                ) {
                    DataCell(text = "TYPES",220.dp)
                    DataCell(text = "NO APPLICATION",180.dp)
                    DataCell(text = "APPEAL")
                    DataCell(text = "VIEW")
                }

                for (applicationDetails in rtiModelState.rtiModel.Applicationdetails){
                    Row(
                        modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                            .border(width = 1.dp,
                                color = Color.LightGray,
                                shape = getBottomLineShape(1.dp)
                            )
                            .padding(top = 4.dp, bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DataCell(text = applicationDetails.TYPE?:"", color = md_theme_dark_background, width = 220.dp)
                        DataCell(text = applicationDetails.PIOSCOUNT?:"", color = md_theme_dark_background, width = 180.dp)
                        DataCell(text = applicationDetails.FAACOUNT?:"", color = md_theme_dark_background)
                        DataCell(text = "View", color = md_theme_dark_onTertiary)
                    }
                }

            }

            Spacer(modifier = Modifier.padding(4.dp))
        }
        zoneWisePieChart(rtiModelState.rtiModel)
    }


}


@Composable
fun monthDate(rnduiState: RTIUIState, onclick: () -> Unit) {


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
                .border(BorderStroke(1.dp, Colors.md_theme_dark_surfaceVariant))
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
fun DataCell(text: String,width: Dp = 120.dp,color: Color = md_theme_dark_onBackground) {
    Text(
        text = " $text",
        modifier = Modifier
            .width(width),
        color = color,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp)
}



@Composable
fun yearWise(rtiuiState: RTIUIState,onclick: (String) -> Unit) {
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
                .border(BorderStroke(1.dp, Colors.md_theme_dark_surfaceVariant))
                .clickable { expanded = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = rtiuiState.monthYear,
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
                        rtiuiState.monthYear = item
                        onclick(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun customDate(rtiuiState: RTIUIState, onclick: (String) -> Unit) {


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
                .border(BorderStroke(1.dp, Colors.md_theme_dark_surfaceVariant))
                .clickable { expanded = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = rtiuiState.customDate,
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
                        rtiuiState.customDate = item
                        onclick(item)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun zoneWisePieChart(rtiModel: RTIModel){
    val arrayList : ArrayList<PieChartData> = ArrayList()
    if (rtiModel.piosli.isNotEmpty()){
        arrayList.add(
            PieChartData(
                "No Application",rtiModel.piosli[0].noofapplication?.toFloat()
            )
        )
    }

    if (rtiModel.Faa.isNotEmpty()){
        arrayList.add(
            PieChartData(
                "Appeal",
                rtiModel.Faa[0].noofapplication?.toFloat()
            )
        )
    }

    Log.d("PieChart", "zoneWisePieChart: ${arrayList}")
    pieChart(arrayList,"NUMBER OF APPLICATIONS")
}

@Composable
fun pieChart(arrayList : ArrayList<PieChartData>, title : String ) {
    // on below line we are creating a column
    // and specifying a modifier as max size.
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(320.dp)) {
        // on below line we are again creating a column
        // with modifier and horizontal and vertical arrangement
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // on below line we are creating a simple text
            // and specifying a text as Web browser usage share
            Text(
                text = title,

                // on below line we are specifying style for our text
                style = TextStyle.Default,

                // on below line we are specifying font family.
                fontFamily = FontFamily.Default,

                // on below line we are specifying font style
                fontStyle = FontStyle.Normal,

                // on below line we are specifying font size.
                fontSize = 16.sp
            )

            // on below line we are creating a column and
            // specifying the horizontal and vertical arrangement
            // and specifying padding from all sides.
            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // on below line we are creating a cross fade and
                // specifying target state as pie chart data the
                // method we have created in Pie chart data class.
                Crossfade(targetState = arrayList, label = "") { pieChartData ->
                    // on below line we are creating an
                    // android view for pie chart.
                    AndroidView(factory = { context ->
                        // on below line we are creating a pie chart
                        // and specifying layout params.
                        PieChart(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                // on below line we are specifying layout
                                // params as MATCH PARENT for height and width.
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )
                            // on below line we are setting description
                            // enables for our pie chart.
                            this.description.isEnabled = false

                            // on below line we are setting draw hole
                            // to false not to draw hole in pie chart
                            this.isDrawHoleEnabled = false

                            // on below line we are enabling legend.
                            this.legend.isEnabled = true

                            // on below line we are specifying
                            // text size for our legend.
                            this.legend.textSize = 14F

                            // on below line we are specifying
                            // alignment for our legend.
                            this.legend.horizontalAlignment =
                                Legend.LegendHorizontalAlignment.CENTER

                            // on below line we are specifying entry label color as white.
                            this.setEntryLabelColor(Colors.md_theme_dark_background.toArgb())
                        }
                    },
                        // on below line we are specifying modifier
                        // for it and specifying padding to it.
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(5.dp), update = {
                            // on below line we are calling update pie chart
                            // method and passing pie chart and list of data.
                            updatePieChartWithData(it, pieChartData)
                        })
                }
            }
        }
    }
}
// on below line we are creating a update pie
// chart function to update data in pie chart.
fun updatePieChartWithData(
    // on below line we are creating a variable
    // for pie chart and data for our list of data.
    chart: PieChart,
    data: List<PieChartData>
) {
    // on below line we are creating
    // array list for the entries.
    val entries = ArrayList<PieEntry>()

    // on below line we are running for loop for
    // passing data from list into entries list.
    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.value ?: 0.toFloat(), item.browserName ?: ""))
    }

    // on below line we are creating
    // a variable for pie data set.
    val ds = PieDataSet(entries, "")

    // on below line we are specifying color
    // int the array list from colors.
    ds.colors = arrayListOf(
        Colors.md_theme_dark_onError.toArgb(),
        Colors.md_theme_light_onTertiaryContainer.toArgb(),
        Colors.md_theme_dark_secondary.toArgb(),
        Colors.md_theme_dark_tertiary.toArgb(),
        Colors.md_theme_dark_onPrimary.toArgb(),
        Colors.textColor.toArgb(),
        Colors.md_theme_dark_inversePrimary.toArgb(),
        Colors.md_theme_light_errorContainer.toArgb(),
        Colors.md_theme_light_secondaryContainer.toArgb()
    )
    // on below line we are specifying position for value
    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    // on below line we are specifying position for value inside the slice.
    ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    // on below line we are specifying
    // slice space between two slices.
    ds.sliceSpace = 2f

    // on below line we are specifying text color
    ds.valueTextColor = Colors.textColor.toArgb()

    // on below line we are specifying
    // text size for value.
    ds.valueTextSize = 18f

    // on below line we are specifying type face as bold.
    ds.valueTypeface = Typeface.DEFAULT_BOLD

    // on below line we are creating
    // a variable for pie data
    val d = PieData(ds)

    // on below line we are setting this
    // pie data in chart data.
    chart.data = d
    chart.isDrawHoleEnabled = true

    // on below line we are
    // calling invalidate in chart.
    chart.invalidate()
}