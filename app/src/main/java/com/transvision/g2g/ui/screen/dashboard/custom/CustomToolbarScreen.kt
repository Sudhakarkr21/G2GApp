package com.transvision.g2g.ui.screen.dashboard.custom

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import android.util.Log
import android.view.ViewGroup
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.transvision.g2g.ui.theme.Colors.bgColor
import com.transvision.g2g.ui.theme.Colors.loginColor
import kotlinx.coroutines.launch


@Composable
public fun getBottomLineShape(lineThicknessDp: Dp) : Shape {
    val lineThicknessPx = with(LocalDensity.current) {lineThicknessDp.toPx()}
    return GenericShape { size, _ ->
        // 1) Bottom-left corner
        moveTo(0f, size.height)
        // 2) Bottom-right corner
        lineTo(size.width, size.height)
        // 3) Top-right corner
        lineTo(size.width, size.height - lineThicknessPx)
        // 4) Top-left corner
        lineTo(0f, size.height - lineThicknessPx)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbarScreen(navController: NavController, title: String, isBack: Boolean){
    val scope = rememberCoroutineScope()
    var isDrawerOpen = remember {
        mutableStateOf(false)
    }
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = loginColor,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = title,color = Color.White,
                fontSize = 18.sp)
        },
        modifier = Modifier.background(loginColor),
        navigationIcon = {
            if (isBack){
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                }
            }else{
                IconButton(onClick = {
                    scope.launch {
                        Log.i("Drawer", "drawer Open: ")
                    }
                }) {
                    Icon(Icons.Filled.Menu, "backIcon", tint = Color.White)
                }
            }
        }
    )
}

@Composable
fun LineChartCompose(lineList: List<Entry>) {
    val context = LocalContext.current



    Column(modifier = Modifier
        .fillMaxWidth()
        .height(320.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Crossfade(targetState = lineList, label = "") { lineEntry ->
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                factory = {
                    LineChart(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                        val xAxis: XAxis = this.getXAxis()

                        xAxis.setDrawLimitLinesBehindData(true)
                        xAxis.position = XAxis.XAxisPosition.BOTTOM

                        val leftAxis: YAxis = axisLeft
                        leftAxis.removeAllLimitLines()
                        leftAxis.setDrawZeroLine(false)
                        leftAxis.setDrawLimitLinesBehindData(false)

                        axisRight.isEnabled = false

                        getXAxis().setDrawGridLines(false);
                        axisLeft.setDrawGridLines(false);
                        axisRight.setDrawGridLines(false);

                        var set1: LineDataSet? = null
                        if (data != null &&
                            data.dataSetCount > 0
                        ) {
                            set1 = data.getDataSetByIndex(0) as LineDataSet
                            set1.values = lineEntry
                            data.notifyDataChanged();
                            notifyDataSetChanged()
                        } else {
                            set1 = LineDataSet(lineEntry, "Total Application");
                            set1.setDrawIcons(false)
                            set1.enableDashedLine(10f, 5f, 0f);
                            set1.enableDashedHighlightLine(10f, 5f, 0f);
                            set1.color = loginColor.toArgb()
                            set1.setCircleColor(loginColor.toArgb())
                            set1.lineWidth = 1f;
                            set1.circleRadius = 3f;
                            set1.setDrawCircleHole(false);
                            set1.valueTextSize = 9f;
                            set1.setDrawFilled(true);
                            set1.formLineWidth = 1f;
                            // set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                            set1.formSize = 15F
                            set1.fillColor = loginColor.toArgb();


                            val dataSets: ArrayList<ILineDataSet> = ArrayList<ILineDataSet>()
                            dataSets.add(set1);
                            val data = LineData(dataSets);
                            setData(data);

                            invalidate()
                        }
                    }
                },
                update = {

                }
            )
        }

    }

}
















