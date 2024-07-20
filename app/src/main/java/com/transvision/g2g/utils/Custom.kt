package com.transvision.g2g.utils

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.transvision.g2g.ui.theme.Colors

@Composable
fun Table(
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    verticalLazyListState: LazyListState = rememberLazyListState(),
    horizontalScrollState: ScrollState = rememberScrollState(),
    columnCount: Int,
    rowCount: Int,
    beforeRow: (@Composable (rowIndex: Int) -> Unit)? = null,
    afterRow: (@Composable (rowIndex: Int) -> Unit)? = null,
    cellContent: @Composable (columnIndex: Int, rowIndex: Int) -> Unit
) {
    val columnWidths = remember { mutableStateMapOf<Int, Int>() }

    Box(modifier = modifier.then(Modifier.horizontalScroll(horizontalScrollState))) {
        LazyColumn(state = verticalLazyListState) {
            items(rowCount) { rowIndex ->
                Column {
                    beforeRow?.invoke(rowIndex)

                    Row(modifier = rowModifier) {
                        (0 until columnCount).forEach { columnIndex ->
                            Box(modifier = Modifier.layout { measurable, constraints ->
                                val placeable = measurable.measure(constraints)

                                val existingWidth = columnWidths[columnIndex] ?: 0
                                val maxWidth = maxOf(existingWidth, placeable.width)

                                if (maxWidth > existingWidth) {
                                    columnWidths[columnIndex] = maxWidth
                                }

                                layout(width = maxWidth, height = placeable.height) {
                                    placeable.placeRelative(0, 0)
                                }
                            }) {
                                cellContent(columnIndex, rowIndex)
                            }
                        }
                    }

                    afterRow?.invoke(rowIndex)
                }
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    alignment: TextAlign = TextAlign.Center,
    title: Boolean = false
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(10.dp),
        fontWeight = if (title) FontWeight.Bold else FontWeight.Normal,
        textAlign = alignment,
        fontSize = 12.sp
    )
}


@Composable
fun DrawScrollableView(content: @Composable () -> Unit, modifier: Modifier) {
    AndroidView(
        modifier = modifier
            .border(width = 1.dp, color = Colors.bgColor),
        factory = {
            val scrollView = ScrollView(it)
            val layout = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            scrollView.layoutParams = layout
            scrollView.isVerticalFadingEdgeEnabled = true
            scrollView.isHorizontalScrollBarEnabled = true
            scrollView.isScrollbarFadingEnabled = false
            scrollView.addView(ComposeView(it).apply {
                setContent {
                    content()
                }
            })
            val linearLayout = LinearLayout(it)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            linearLayout.addView(scrollView)
            linearLayout
        }
    )
}

@Preview
@Composable
fun customDraw(){
    val instaColors = listOf(Color.Yellow, Color.Red, Color.Magenta)

    Canvas(modifier = Modifier
        .padding(20.dp)
        .size(100.dp)
        .background(Color.LightGray),
        onDraw = {
            drawRoundRect(
                brush = Brush.linearGradient(colors = instaColors),
                cornerRadius = CornerRadius(60f, 60f),
                style = Stroke(width = 15f, cap = StrokeCap.Round)
            )
            drawCircle(
                brush = Brush.linearGradient(colors = instaColors),
                radius = 45f,
                style = Stroke(width = 15f, cap = StrokeCap.Round)
            )
            drawCircle(
                brush = Brush.linearGradient(colors = instaColors),
                radius = 30f,
                center = Offset(this.size.width * .80f, this.size.height * 0.20f),
            )
        }
    )
}