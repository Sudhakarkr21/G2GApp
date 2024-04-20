package com.transvision.g2g.ui.screen.dashboard.dss

import android.R.attr
import android.graphics.Typeface
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.transvision.g2g.ui.screen.dashboard.RTI.DataCell
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.custom.getBottomLineShape
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.columnheaderbg
import com.transvision.g2g.ui.theme.headerbg
import com.transvision.g2g.ui.theme.md_theme_dark_background
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.DrawScrollableView
import com.transvision.g2g.utils.MonthPicker


@Composable
fun DssDashboard(navController: NavController) {

    val dssViewModel : DSSViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.md_theme_light_onPrimary)
            .verticalScroll(rememberScrollState())
    ) {

        CustomToolbarScreen(navController = navController, title = "DSS Dashboard", true)

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            monthDate(onclick = {
                dssViewModel.getDSSData(it)
            })

            barChartDSS(dssViewModel)
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
                        text = "CASH BALANCE", modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 10.dp)

                    )
                }
            }
            cashBalanceDataScreen(dssViewModel)
            Column(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(headerbg)
                ) {
                    Text(
                        text = "BANK BALANCE", modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 10.dp)

                    )
                }
            }
            bankBalanceDataScreen(dssViewModel)
        }

    }
}


@Composable
fun cashBalanceDataScreen(dssViewModel : DSSViewModel) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(columnheaderbg)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "Account Code", 100.dp,md_theme_dark_background)
            DataCell(text = "Description", 230.dp,md_theme_dark_background)
            DataCell(text = "Balance", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(140.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp)
            ,
            content = {
                Column {
                    for (item in dssViewModel.state.value.dssModel.CashBalanceTableData){
                        Row(
                            modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                                .border(width = 1.dp,
                                    color = Color.LightGray,
                                    shape = getBottomLineShape(1.dp))
                                .padding(top = 4.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            DataCell(item.AccountCode?:"0", color = Colors.md_theme_dark_background, width = 100.dp)
                            DataCell(
                                text = item.Description?:"",
                                color = Colors.md_theme_dark_background,
                                width = 230.dp
                            )
                            DataCell(
                                text = item.Balance?:"",
                                color = Colors.md_theme_dark_background
                            )
                        }
                    }
                }
            })
    }
}
@Composable
fun bankBalanceDataScreen(dssViewModel: DSSViewModel) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(columnheaderbg)
                .horizontalScroll(scrollState),
        ) {
            DataCell(text = "Account Code", 100.dp,md_theme_dark_background)
            DataCell(text = "Description", 280.dp,md_theme_dark_background)
            DataCell(text = "Balance", color = md_theme_dark_background)
        }
        DrawScrollableView(modifier = Modifier
            .height(140.dp)
            .horizontalScroll(scrollState)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp),
            content = {
                Column {
                    for (item in dssViewModel.state.value.dssModel.BankBalanceTable){
                        Row(
                            modifier = Modifier.background(Colors.md_theme_light_onSecondary)
                                .border(width = 1.dp,
                                    color = Color.LightGray,
                                    shape = getBottomLineShape(1.dp)
                                )
                                .padding(top = 4.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            DataCell(item.AccountCode?:"", color = Colors.md_theme_dark_background, width = 100.dp)
                            DataCell(
                                text = item.Description?:"",
                                color = Colors.md_theme_dark_background,
                                width = 280.dp
                            )
                            DataCell(
                                text = item.Balance?:"",
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
                    onclick("$year_-$month_")
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
fun barChartDSS(dssViewModel : DSSViewModel){
    val arrayList = ArrayList<BarEntry>()
    if (dssViewModel.state.value.dssModel.GraphData.isNotEmpty()){
        arrayList.add(BarEntry(1F,
            dssViewModel.state.value.dssModel.GraphData[0].Assets?.toFloat()?:0.0F))
        arrayList.add(BarEntry(2F,
            dssViewModel.state.value.dssModel.GraphData[0].expanditure?.toFloat()?:0.0F))
        arrayList.add(BarEntry(3F,
            dssViewModel.state.value.dssModel.GraphData[0].income?.toFloat()?:0.0F))
        arrayList.add(BarEntry(4F,
            dssViewModel.state.value.dssModel.GraphData[0].liability?.toFloat()?:0.0F))
    }

    barChart(arrayList = arrayList, title = "KAS CALCULATIONS")
}

@Composable
fun barChart(arrayList : ArrayList<BarEntry>, title : String ) {
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
            verticalArrangement = Arrangement.Bottom
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
                        BarChart(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                // on below line we are specifying layout
                                // params as MATCH PARENT for height and width.
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )
                            this.setMaxVisibleValueCount(pieChartData.size)
                            // on below line we are setting description
                            // enables for our pie chart.
                            this.description.isEnabled = false
                            // on below line we are enabling legend.
                            this.legend.isEnabled = true

                            // on below line we are specifying
                            // text size for our legend.
                            this.legend.textSize = 14F


                            val xLabels = ArrayList<String>()
                            xLabels.add("Assets")
                            xLabels.add("Expenditure")
                            xLabels.add("Income")
                            xLabels.add("Liability")

                            val xAxisFormatter = object : IndexAxisValueFormatter() {
                                override fun getFormattedValue(value: Float): String {
                                    return xLabels[value.toInt() - 1]
                                }
                            }

                            val xAxis = this.xAxis
                            xAxis.valueFormatter = xAxisFormatter;
                            xAxis.setLabelCount(4, true)
                            xAxis.valueFormatter = xAxisFormatter
                            xAxis.labelCount = 4
                            xAxis.granularity = 1F
                            xAxis.isGranularityEnabled = true

                            xAxis.position = XAxis.XAxisPosition.BOTTOM
                            xAxis.setDrawGridLines(false)


                            val leftAxis = this.axisLeft
                            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
                            leftAxis.spaceTop = 0f
                            leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

                            val yAxisRight: YAxis = this.axisRight
                            yAxisRight.isEnabled = false

                            val l = this.legend
                            l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                            l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                            l.orientation = Legend.LegendOrientation.HORIZONTAL
                            l.setDrawInside(false)
                            l.form = Legend.LegendForm.NONE
                            l.formSize = 9f
                            l.textSize = 11f
                            l.xEntrySpace = 1f




                        }
                    },
                        // on below line we are specifying modifier
                        // for it and specifying padding to it.
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(5.dp), update = {
                            // on below line we are calling update pie chart
                            // method and passing pie chart and list of data.
                            updateBarChartWithData(it, pieChartData)
                        })
                }
            }
        }
    }
}
// on below line we are creating a update pie
// chart function to update data in pie chart.
fun updateBarChartWithData(
    // on below line we are creating a variable
    // for pie chart and data for our list of data.
    chart: BarChart,
    data: List<BarEntry>
) {


    // on below line we are creating
    // array list for the entries.

    // on below line we are creating
    // a variable for pie data set.
    val ds = BarDataSet(data, "")
    ds.setDrawValues(true)
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
    chart.setVisibleYRangeMaximum(ds.yMax + 20, YAxis.AxisDependency.LEFT);

    // on below line we are specifying text color
    ds.valueTextColor = Colors.textColor1.toArgb()

    /*// on below line we are specifying
    // text size for value.
    ds.valueTextSize = 14f

    // on below line we are specifying type face as bold.
    ds.valueTypeface = Typeface.MONOSPACE*/

    // on below line we are creating
    // a variable for pie data
    val d = BarData(ds)

    // on below line we are setting this
    // pie data in chart data.
    chart.data = d

    // on below line we are
    // calling invalidate in chart.
    chart.invalidate()
}