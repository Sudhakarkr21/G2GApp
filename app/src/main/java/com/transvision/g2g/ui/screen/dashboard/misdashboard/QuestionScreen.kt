package com.transvision.g2g.ui.screen.dashboard.misdashboard

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.TableCell

@Composable
fun QuestionScreen(){


    val TAG = "MISDashBoardScreen"
    val viewModel: MisDashBoardViewModel = hiltViewModel()
    val misUIEvent by remember {
        mutableStateOf(
            MISUIEvent(
                (Constants.getCurrentFinancialYear()),
                SessionModel(1,"Budget Session")
            )
        )
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        financialDropDown(misUIEvent,
            onSelected = {
                viewModel.getMISDashBoardData(misUIEvent.Session.SESSIONSID.toString(),misUIEvent.financialYear)
            }
        )
        sessionNameDropDown(viewModel.state.value.listSession, misUIEvent, dropDownSelected = {
            viewModel.getMISDashBoardData(misUIEvent.Session.SESSIONSID.toString(),misUIEvent.financialYear)
        }
        )
        stateAssemblyInfo(viewModel.dashBoardState.value.misDashBoardModel)
    }
}


@Composable
fun financialDropDown(selectedItem: MISUIEvent,onSelected : () -> Unit) {
    val items = Constants.generateFinancialYears(Constants.getCurrentYear - 10, 14)

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Financial Year :",
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
                    text = selectedItem.financialYear,
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
                        selectedItem.financialYear = item
                        onSelected()
                    }
                    )
                }
            }
        }
    }
}

@Composable
fun sessionNameDropDown(list: List<SessionModel>, selectedItem: MISUIEvent,dropDownSelected : () -> Unit) {


    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Sessions Name :",
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
                    text = selectedItem.Session.SESSIONSNAME ?: "Select Session",
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
                list.forEach { item ->
                    DropdownMenuItem(text = {
                        Text(text = item.SESSIONSNAME ?: "NONE")
                    }, onClick = {
                        expanded = false
                        selectedItem.Session = item
                        dropDownSelected()
                    }
                    )
                }
            }
        }
    }
}

@Composable
fun stateAssemblyInfo(misDashBoardModel: MISDashBoardModel) {

    val headerAssembly = listOf<String>("Question Type", "Total Question", "Answered", "Pending")

    val columnWeight = (1.00 / headerAssembly.size).toFloat()
    Column(modifier = Modifier.padding(8.dp)) {
        LazyColumn() {
            item {
                Text(text = "ASSEMBLY & PARLIMENT QUESTIONS ", color = Colors.textColor1, fontSize = 16.sp)
                if (misDashBoardModel.TableDataAssmble.isNotEmpty())
                    PieChart(misDashBoardModel)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "State Assembly ", color = Colors.md_theme_dark_scrim, fontSize = 14.sp)
                Spacer(modifier = Modifier.padding(5.dp))

                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Colors.bgColor),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    TableCell(
                        text = "Question Type",
                        weight = columnWeight,
                        alignment = TextAlign.Left,
                        title = true
                    )
                    TableCell(text = "Total Question", weight = columnWeight, title = true)
                    TableCell(text = "Answered", weight = columnWeight, title = true)
                    TableCell(
                        text = "Pending",
                        weight = columnWeight,
                        alignment = TextAlign.Right,
                        title = true
                    )
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
            itemsIndexed(misDashBoardModel.TableDataAssmble) { _, tableDataAssmble ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Colors.md_theme_light_surface),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableCell(
                        text = tableDataAssmble.QuestionType ?: "0",
                        weight = columnWeight,
                        alignment = TextAlign.Left
                    )
                    TableCell(text = tableDataAssmble.TotalCount ?: "0", weight = columnWeight)
                    TableCell(text = tableDataAssmble.AnsCount ?: "0", weight = columnWeight)
                    TableCell(
                        text = tableDataAssmble.PendingCount ?: "0",
                        weight = columnWeight,
                        alignment = TextAlign.Right
                    )
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
            item {
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Central  Parliament ", color = Colors.md_theme_dark_scrim, fontSize = 14.sp)
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Colors.bgColor),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    TableCell(
                        text = "Question Type",
                        weight = columnWeight,
                        alignment = TextAlign.Left,
                        title = true
                    )
                    TableCell(text = "Total Question", weight = columnWeight, title = true)
                    TableCell(text = "Answered", weight = columnWeight, title = true)
                    TableCell(
                        text = "Pending",
                        weight = columnWeight,
                        alignment = TextAlign.Right,
                        title = true
                    )
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
            itemsIndexed(misDashBoardModel.TableDataParliment) { _, tableDataAssmble ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Colors.md_theme_light_surface),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableCell(
                        text = tableDataAssmble.QuestionType ?: "0",
                        weight = columnWeight,
                        alignment = TextAlign.Left
                    )
                    TableCell(text = tableDataAssmble.TotalCount ?: "0", weight = columnWeight)
                    TableCell(text = tableDataAssmble.AnsCount ?: "0", weight = columnWeight)
                    TableCell(
                        text = tableDataAssmble.PendingCount ?: "0",
                        weight = columnWeight,
                        alignment = TextAlign.Right
                    )
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }

}

// on below line we are creating a
// pie chart function on below line.
@Composable
fun PieChart(misDashBoardModel: MISDashBoardModel) {
    // on below line we are creating a column
    // and specifying a modifier as max size.
    val arrayList : ArrayList<PieChartData> = ArrayList()
    arrayList.add(PieChartData("Assembly",misDashBoardModel.TableDataAssmble[misDashBoardModel.TableDataAssmble.lastIndex].TotalCount?.toFloat()))
    arrayList.add(PieChartData("Parliament",misDashBoardModel.TableDataParliment[misDashBoardModel.TableDataParliment.lastIndex].TotalCount?.toFloat()))
    Column(modifier = Modifier.fillMaxSize()) {
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
                text = "QUESTIONS AND REPLIES",

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
                    .size(220.dp),
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