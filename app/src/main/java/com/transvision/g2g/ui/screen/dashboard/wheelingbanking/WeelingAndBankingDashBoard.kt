package com.transvision.g2g.ui.screen.dashboard.wheelingbanking

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.transvision.g2g.ui.theme.Colors
import com.transvision.g2g.utils.Constants
import com.transvision.g2g.utils.MonthPicker

@Preview
@Composable
fun wheelingBankingDashBoard(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.md_theme_light_onPrimary)
            .verticalScroll(rememberScrollState())
    ){
        monthDate(onclick = {})

        Spacer(modifier = Modifier.padding(4.dp))
        cardData("W&B",Icons.Filled.Person,"220","OLD","110"
            ,"GEOA","120")
        cardData("C-FORM",Icons.Filled.Menu,"245","APPROVAL",
            "11","PENDING","12")
        cardData("B-FORM",Icons.Filled.NetworkCell,"245","APPROVAL",
            "11","PENDING","12")
        cardData("DCU DATA",Icons.Filled.DocumentScanner,"245","APPROVAL",
            "11","PENDING","12")
    }


}

@Composable
fun cardData(title : String,imageVector: ImageVector,application : String,
             approvalTextView : String,approvalValueText : String,
             pendingTextView : String,pendingValueText : String){
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
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
                modifier = Modifier.constrainAs(textTitle){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
            Icon(
                imageVector = imageVector,
                contentDescription = "MIS",
                modifier = Modifier.constrainAs(imgTile){
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
            Text(text = approvalValueText,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(approveValue){
                    top.linkTo(applicationText.bottom)
                    start.linkTo(parent.start)
                },
                fontSize = 22.sp,
            )
            Text(text = pendingValueText,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(pendingValue){
                    top.linkTo(applicationValue.bottom)
                    end.linkTo(parent.end)
                },
                fontSize = 22.sp,
            )
            Text(text = approvalTextView,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(approveText){
                    top.linkTo(approveValue.bottom)
                    end.linkTo(approveValue.end)
                    start.linkTo(approveValue.start)
                },
                fontSize = 22.sp,
            )
            Text(text = pendingTextView,
                color = Colors.md_theme_dark_outlineVariant,
                modifier = Modifier.constrainAs(pendingText){
                    top.linkTo(pendingValue.bottom)
                    end.linkTo(pendingValue.end)
                    start.linkTo(pendingValue.start)
                },
                fontSize = 22.sp,
            )

            createHorizontalChain(approveValue,pendingValue, chainStyle = ChainStyle.Spread)
        }
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
                    onclick("$month_-$year_")
                },
                cancelClicked = {
                    visible = false
                }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
    }
}