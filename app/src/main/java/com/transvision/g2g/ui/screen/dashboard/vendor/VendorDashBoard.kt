package com.transvision.g2g.ui.screen.dashboard.vendor

import android.graphics.Typeface
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import com.transvision.g2g.ui.screen.dashboard.misdashboard.PieChartData
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.AccidentModel
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.columnText
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.md_theme_dark_onSurfaceVariant
import dagger.hilt.android.lifecycle.HiltViewModel

@Preview
@Composable
fun VendorDashBoard(navController: NavController){

    val vendorViewModel : VendorViewModel = hiltViewModel()

    val vendorState by remember {
        vendorViewModel.state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.md_theme_light_onTertiary)
    ) {
        LazyColumn{
            item {
                CustomToolbarScreen(navController = navController, title = "Vendor Dashboard", true)
                Spacer(modifier = Modifier.padding(4.dp))
                if (vendorState.vendorModel.NumberofApplications.isNotEmpty()){
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
                                    text = "Pending Application",
                                    color = Colors.seed,
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                                Text(
                                    text = vendorState.vendorModel.NumberofApplications[1].COUNT?:"" ,
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
                                Text(text = "Approved Application",
                                    color = Colors.seed,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 4.dp))
                                Text(
                                    text = vendorState.vendorModel.NumberofApplications[0].COUNT?:"",
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
                                Text(text = "Rejected Application",
                                    color = Colors.seed,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 4.dp))
                                Text(
                                    text = vendorState.vendorModel.NumberofApplications[2].COUNT?:"",
                                    color = Colors.md_theme_dark_inverseOnSurface,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    zoneWisePieChart(vendorState.vendorModel.NumberofApplications)
                    Spacer(modifier = Modifier.padding(4.dp))
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
            }

            items(vendorState.vendorModel.ApplicationDetails){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(BorderStroke(2.dp, md_theme_dark_onSurfaceVariant))
                ){
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp)
                    ) {
                        columnText("SLNO",  it.SLNO?:"0")
                        columnText("APPLICATION",  it.APPLICATION?:"")
                        columnText("VENDOR ID",  it.VENDORID?:"0")
                    }
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp)
                    ) {
                        columnText("MATERIAL NAME",  it.MATERIALNAME?:"")
                    }
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp)
                    ) {
                        columnText("APPLICATION DATE",  it.APPLICATIONDATE?:"")
                        columnText("APPLICATION",  it.APPLICATION?:"")
                        columnText("VENDOR ID",  it.VENDORID?:"")
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
        
    }


}


@Composable
fun RowScope.columnText(title: String, data: String) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .weight(1F)
            .padding(start = 4.dp)
    ) {
        Text(
            text = title,
            color = Colors.seed,
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold
        )
        Text(text = data, color = Colors.md_theme_dark_inverseOnSurface, fontSize = 14.sp)

    }
}


@Composable
fun zoneWisePieChart(numberofApplications : ArrayList<NumberofApplications>){
    val arrayList : ArrayList<PieChartData> = ArrayList()
    arrayList.add(
        PieChartData(
            "Pending",numberofApplications[1].COUNT?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "Approved",
            numberofApplications[0].COUNT?.toFloat()
        )
    )
    arrayList.add(
        PieChartData(
            "Rejected",
            numberofApplications[2].COUNT?.toFloat()
        )
    )

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