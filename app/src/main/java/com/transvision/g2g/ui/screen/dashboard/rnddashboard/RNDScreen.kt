package com.transvision.g2g.ui.screen.dashboard.rnddashboard

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.transvision.g2g.ui.screen.dashboard.custom.CustomToolbarScreen
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.escomsWisePieChart
import com.transvision.g2g.ui.screen.dashboard.misdashboard.accident.zoneWisePieChart
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.ui.theme.Colors.md_theme_dark_inverseOnSurface
import com.transvision.g2g.ui.theme.Colors.md_theme_light_onPrimary
import com.transvision.g2g.ui.theme.Colors.md_theme_light_onTertiary
import com.transvision.g2g.ui.theme.Colors.seed
import com.transvision.g2g.ui.theme.md_theme_dark_inverseSurface
import com.transvision.g2g.ui.theme.md_theme_dark_onSurfaceVariant
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.Constants.currentMonth
import com.transvision.g2g.utils.Constants.getCurrentYear
import com.transvision.g2g.utils.Constants.monthYear
import com.transvision.g2g.utils.Constants.months
import com.transvision.g2g.utils.MonthPicker
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Calendar


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RNDScreen(navController: NavController) {

    val rndViewModel: RNDViewModel = hiltViewModel()
    val rndModelState by remember {
        rndViewModel.state
    }
    var isViewVisible by remember { mutableStateOf(true) }

    var isQuarterViewVisible by remember { mutableStateOf(false) }

    var rnduiState by remember {
        mutableStateOf(
            RNDUIState("Month Wise", monthYear, "", "ALL")
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(md_theme_light_onTertiary)
    ) {
        CustomToolbarScreen(navController = navController, title = "RND Dashboard", true)
        LazyColumn {
            item {
                Log.d("RNDScreen", "RNDScreen: ${rnduiState}")
                customDate(rnduiState, onclick = {

                    rnduiState = rnduiState.copy(
                        customDate = rnduiState.customDate,
                        monthYear = if (rnduiState.customDate == "Month Wise") monthYear else Constants.getCurrentFinancialYear(),
                        quarter = if (rnduiState.customDate == "Quarterly Wise") "Quarter1 (Q1)" else ""
                    )

                    rndViewModel.getRNDDashboard(rnduiState)


                    isViewVisible = (rnduiState.customDate == "Month Wise")
                    isQuarterViewVisible = (rnduiState.customDate == "Quarterly Wise")
                })
                if (isViewVisible) {
                    monthDate(rnduiState, onclick = {
                        rndViewModel.getRNDDashboard(rnduiState)
                    })
                }

                if (!isViewVisible)
                    yearWise(rnduiState, onclick = {
                        rndViewModel.getRNDDashboard(rnduiState)
                    })
                if (isQuarterViewVisible)
                    quarterWise(rnduiState, onclick = {
                        rndViewModel.getRNDDashboard(rnduiState)
                    })
                zoneWise(rnduiState, onclick = {
                   // rtViewModel.getRTData(rnduiState)
                })
                oilTanTestDataView(rndModelState)
                oilTableData()

            }
            items(rndModelState.rndModel.OilTesthandsontable) {
                oilTableDataList(it)
            }
            item {
                tenDeltaTestView()
            }
            items(rndModelState.rndModel.TandeltaTesthandsonTable) {
                tenDeltaDataList(it)
            }
        }

    }

}

@Composable
fun tenDeltaTestView() {
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
                text = "TENDELTA TEST", modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp)

            )
        }


    }
}

@Composable
fun tenDeltaDataList(tandeltaTesthandsonTable: TandeltaTesthandsonTable) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, md_theme_dark_onSurfaceVariant))
    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {

            columnText("SL No", tandeltaTesthandsonTable.SlNo ?: "0")
            columnText("Equipment Type", tandeltaTesthandsonTable.EquipmentType ?: "")
            columnText("Equipment Count", tandeltaTesthandsonTable.EquipmentCount ?: "")
        }
    }
}

@Composable
fun oilTableDataList(oilTesthandsontable: OilTesthandsontable) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(BorderStroke(2.dp, md_theme_dark_onSurfaceVariant))
    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            columnText("Oil Type", oilTesthandsontable.OilType ?: "")
            columnText("Sub Oil Type", oilTesthandsontable.SubOilType ?: "")
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            columnText("Zone Name", oilTesthandsontable.ZoneName ?: "")
            columnText("Circle Name", oilTesthandsontable.CircleName ?: "")

        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            columnText("Division Name", oilTesthandsontable.DivisionName ?: "")
            columnText("Station Name", oilTesthandsontable.StationName ?: "")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            columnText("TF SlNo", oilTesthandsontable.TFSLNO ?: "")
            columnText("Equipment Type", oilTesthandsontable.EquipmentType ?: "")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {

            columnText("TF Name", oilTesthandsontable.TFName ?: "")
            columnText("OLTC Flag", oilTesthandsontable.OLTCFlag ?: "")
            columnText("Flag", oilTesthandsontable.Flag ?: "")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            columnText("Apperance", oilTesthandsontable.Appearance ?: "")
            columnText("Acidity", oilTesthandsontable.Acidity ?: "")
            columnText("Dielectric", oilTesthandsontable.Dielectric ?: "")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            columnText("Kinematic Viscocity", oilTesthandsontable.KinematicViscocity ?: "")
            columnText("Flash Point", oilTesthandsontable.FlashPoint ?: "")
            columnText("Interfacial Tension", oilTesthandsontable.InterfacialTension ?: "")
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            columnText("BreakDown voltage", oilTesthandsontable.BreakdownVoltage ?: "")
            columnText("BreakDown voltage B", oilTesthandsontable.BreakdownVoltageB ?: "")
            columnText("Water Content", oilTesthandsontable.WaterContent ?: "")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            columnText("Resistivity", oilTesthandsontable.Resistivity ?: "")
            columnText("Density", oilTesthandsontable.Density ?: "")
            columnText("Date", oilTesthandsontable.Date ?: "")
        }

        Spacer(modifier = Modifier.padding(4.dp))
    }
    Spacer(modifier = Modifier.padding(4.dp))

}

@Composable
fun oilTableData() {
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
                text = "OIL TEST", modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp)

            )
        }


    }
}

@Composable
fun RowScope.columnText(title: String, data: String) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.weight(1F).padding(start = 4.dp)
    ) {
        Text(
            text = title,
            color = seed,
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold
        )
        Text(text = data, color = md_theme_dark_inverseOnSurface, fontSize = 14.sp)

    }
}

@Composable
fun oilTanTestDataView(rndState: RNDState) {
    Spacer(modifier = Modifier.padding(4.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp).padding(bottom = 8.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .weight(1F)
                .height(100.dp).background(md_theme_light_onPrimary),
            colors = CardDefaults.cardColors(
                containerColor = md_theme_light_onPrimary,
            ),
        ) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Oil Test",
                    color = seed,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = rndState.rndModel.Oil ?: "0",
                    color = md_theme_dark_inverseOnSurface,
                    fontSize = 16.sp
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
                containerColor = md_theme_light_onPrimary,
            )
        ) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "TanDelta Test", color = seed, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = rndState.rndModel.Tandelta ?: "0",
                    color = md_theme_dark_inverseOnSurface,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun zoneWise(rnduiState: RNDUIState, onclick: () -> Unit) {
    val items =
        listOf<String>("ALL", "Bangalore", "Tumkur", "Mysuru", "Hassan", "Bagalkot", "Kalaburagi")

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("ALL") }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Zone :",
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
                    text = rnduiState.zone,
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
                        rnduiState.zone = item
                        onclick()
                    }
                    )
                }
            }
        }
    }

}

@Composable
fun quarterWise(rnduiState: RNDUIState, onclick: () -> Unit) {
    val items = listOf<String>("Quarter1 (Q1)", "Quarter2 (Q2)", "Quarter3 (Q3)", "Quarter4 (Q4)")

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Quarter1 (Q1)") }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Select Quarter :",
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
                    text = rnduiState.quarter,
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
                        rnduiState.quarter = item
                        onclick()
                    }
                    )
                }
            }
        }
    }
}

@Composable
fun yearWise(rnduiState: RNDUIState, onclick: () -> Unit) {
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
                    text = rnduiState.monthYear,
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
                        rnduiState.monthYear = item
                        onclick()
                    }
                    )
                }
            }
        }
    }
}

@Composable
fun monthDate(rnduiState: RNDUIState, onclick: () -> Unit) {


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
                currentMonth = currentMonth,
                currentYear = getCurrentYear,
                confirmButtonCLicked = { month_, year_ ->

                    rnduiState.monthYear = "${months[month_ - 1]}-$year_"
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
fun customDate(rnduiState: RNDUIState, onclick: () -> Unit) {


    val items = listOf<String>("Month Wise", "Year Wise", "Quarterly Wise")

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