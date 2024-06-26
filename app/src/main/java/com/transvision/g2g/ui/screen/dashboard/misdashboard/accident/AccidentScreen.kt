package com.transvision.g2g.ui.screen.dashboard.misdashboard.accident

import android.graphics.Typeface
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.transvision.g2g.ui.screen.dashboard.misdashboard.MISDashBoardModel
import com.transvision.g2g.ui.screen.dashboard.misdashboard.PieChartData
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.utils.MonthPicker
import java.util.Calendar

@Preview
@Composable
fun accidentScreen(){

    val accidentViewModel : MisAccidentViewModel = hiltViewModel()

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)
    val months = listOf(
        "JAN",
        "FEB",
        "MAR",
        "APR",
        "MAY",
        "JUN",
        "JUL",
        "AUG",
        "SEP",
        "OCT",
        "NOV",
        "DEC"
    )

    var visible by remember {
        mutableStateOf(false)
    }

    var date by remember {
        mutableStateOf("${months[currentMonth]}-$year")
    }



    Column(modifier = Modifier
        .padding(horizontal = 5.dp, vertical = 10.dp)
        .verticalScroll(
            rememberScrollState()
        )) {
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
                    text = date,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "arrow"
                )
            }

            MonthPicker(
                visible = visible,
                currentMonth = currentMonth,
                currentYear = year,
                confirmButtonCLicked = { month_, year_ ->
                    date = "${months[month_-1]}-$year_"
                    accidentViewModel.getAccidentalDashboard("$year_-${String.format("%02d", month_)}")
                    visible = false
                },
                cancelClicked = {
                    visible = false
                }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
        if (accidentViewModel.state.value.accidentModel.ZonesCount.isNotEmpty()){
            //Text(text = "ZONE WISE ACCIDENTS", color = Colors.textColor1, fontSize = 16.sp)
            zoneWisePieChart(accidentViewModel.state.value.accidentModel)
        }
        if (accidentViewModel.state.value.accidentModel.EscomsCount.isNotEmpty()){
            //Text(text = "ESCOMS WISE ACCIDENTS", color = Colors.textColor1, fontSize = 16.sp)
            escomsWisePieChart(accidentViewModel.state.value.accidentModel)
        }
    }
    
}

@Composable
fun escomsWisePieChart(accidentModel: AccidentModel){
    val arrayList : ArrayList<PieChartData> = ArrayList()
    arrayList.add(
        PieChartData(
            "Human",accidentModel.EscomsCount[0].HUMANCOUNT?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "Animal",accidentModel.EscomsCount[0].ANIMALCOUNT?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "OTHER",accidentModel.EscomsCount[0].OTHERCOUNT?.toFloat()
        )
    )

    pieChart(arrayList,"ESCOMS WISE ACCIDENTS")
}

@Composable
fun zoneWisePieChart(accidentModel: AccidentModel){
    val arrayList : ArrayList<PieChartData> = ArrayList()
    arrayList.add(
        PieChartData(
            "Human",accidentModel.ZonesCount[0].HUMANCOUNT?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "Animal",accidentModel.ZonesCount[0].ANIMALCOUNT?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "OTHER",accidentModel.ZonesCount[0].OTHERCOUNT?.toFloat()
        )
    )

    Log.d("PieChart", "zoneWisePieChart: ${arrayList}")
    pieChart(arrayList,"ZONE WISE ACCIDENTS")
}

@Composable
fun pieChart(arrayList : ArrayList<PieChartData>,title : String ) {
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
                            this.setEntryLabelColor(Colors.md_theme_dark_onTertiaryContainer.toArgb())
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
        Colors.md_theme_dark_onPrimary.toArgb()
    )
    // on below line we are specifying position for value
    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    // on below line we are specifying position for value inside the slice.
    ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    // on below line we are specifying
    // slice space between two slices.
    ds.sliceSpace = 2f

    // on below line we are specifying text color
    ds.valueTextColor = Colors.bgColor.toArgb()

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

    // on below line we are
    // calling invalidate in chart.
    chart.invalidate()
}