package com.transvision.g2g.ui.screen.approva

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DesignServices
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.transvision.g2g.R
import com.transvision.g2g.ui.theme.AppTheme

@Composable
fun ApprovalItem(onclick: () -> Unit, counnt: Int) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                AppTheme.dimens.paddingSmall


            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight()
                    .background(
                        if (counnt == 1) Color(0xFFFF9800)
                        else if (counnt < 4) Color(0xFF008000)
                        else Color(0xFFD22B2B)
                    ) // Set the background color here
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .clickable {
                        onclick()
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Person,
                        contentDescription = "user",
                        modifier = Modifier.size(30.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sudhakar $counnt",
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W500,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Text(
                            modifier = Modifier
                                .border(
                                    2.dp,
                                    if (counnt == 1) Color(0xFFFF9800)
                                    else if (counnt < 4) Color(0xFF008000)
                                    else Color(0xFFD22B2B)

                                )
                                .padding(start = 8.dp, end = 8.dp, top = 3.dp, bottom = 3.dp),
                            text = if (counnt == 1) "PENDING"
                            else if (counnt < 4) "APROVED"
                            else "REJECTED",
                            textAlign = TextAlign.End,
                            color = if (counnt == 1) Color(0xFFFF9800)
                            else if (counnt < 4) Color(0xFF008000)
                            else Color(0xFFD22B2B),
                            fontSize = 10.sp
                        )
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DesignServices,
                        contentDescription = "user",
                        modifier = Modifier.size(30.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "AAO",
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "next screen"
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    ApprovalItem(onclick = { /*TODO*/ }, counnt = 1)
}