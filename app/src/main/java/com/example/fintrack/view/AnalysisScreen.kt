package com.example.fintrack.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fintrack.R
import com.example.fintrack.model.Expense
import com.example.fintrack.model.categoryItems
import com.example.fintrack.ui.theme.FinTrackTheme
import com.example.fintrack.viewModel.ExpenseViewModel

@Composable
fun AnalysisScreen(
    navController: NavController,
    language: Boolean,
    expenseViewModel: ExpenseViewModel,
    expenseByDays: List<Expense>
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(1) }
    var index = 1
    val pieChart = getPercentageOfAmountsWithCategory(expenseByDays)
    var textInPieChart =
        "${stringResource(R.string.Spent)} \n ${expenseByDays.sumOf { it.amount }} ${
            if (language) stringResource(R.string.USD) else stringResource(R.string.UAH)
        }"

    FinTrackTheme {

        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController, index)
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.AnalysisScreen),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.padding(bottom = 50.dp, top = 5.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .size(280.dp, 60.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clickable { selectedIndex = 0; expenseViewModel.getExpensesByWeek() }
                            .clip(CircleShape)
                            .background(if (selectedIndex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer)
                            .size(64.dp, 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.week),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (selectedIndex == 0) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clickable { selectedIndex = 1; expenseViewModel.getExpensesByMonth() }
                            .clip(CircleShape)
                            .background(if (selectedIndex == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer)
                            .size(64.dp, 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.month),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (selectedIndex == 1) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clickable { selectedIndex = 2; expenseViewModel.getExpensesByYear() }
                            .clip(CircleShape)
                            .background(if (selectedIndex == 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer)
                            .size(64.dp, 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.year),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (selectedIndex == 2) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
                        )
                    }
                }

                MyPieChart(
                    pieChart, textInPieChart
                )
                Column(modifier = Modifier.padding(top = 20.dp)) {
                    pieChart.forEach { item ->
                        Row(
                            modifier = Modifier.padding(bottom = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(46.dp)
                                    .background(item.key),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = when (item.key) {
                                        Color(0xFF5D3F65) -> categoryItems[0].icon
                                        Color(0xFF2A3F6D) -> categoryItems[1].icon
                                        Color(0xFF6F3F41) -> categoryItems[2].icon
                                        Color(0xFF3C5E51) -> categoryItems[3].icon
                                        else -> Icons.Default.Close
                                    },
                                    contentDescription = null,
                                    tint = when (item.key) {
                                        Color(0xFF5D3F65) -> categoryItems[0].mainColor
                                        Color(0xFF2A3F6D) -> categoryItems[1].mainColor
                                        Color(0xFF6F3F41) -> categoryItems[2].mainColor
                                        Color(0xFF3C5E51) -> categoryItems[3].mainColor
                                        else -> MaterialTheme.colorScheme.error
                                    },
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(33.dp)
                                        .background(item.key)
                                )
                            }
                            Text(
                                " : " + "%.1f".format(item.value) + '%',
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PieChart(
    data: Map<Color, Float>,
    textColor: Color,
    centerCircleColor: Color,
    centerText: String,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = modifier.size(200.dp)) {
        var startAngle = 0f

        data.forEach { (color, percentage) ->
            val sweepAngle = 360 * (percentage / 100f)
            drawPieSlice(startAngle, sweepAngle, color)
            startAngle += sweepAngle
        }

        val centerRadius = size.minDimension / 3
        drawCircle(
            color = centerCircleColor,
            radius = centerRadius
        )

        val textStyle = TextStyle(
            color = textColor, fontFamily = FontFamily(Font(R.font.poppinssemibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp, textAlign = TextAlign.Center
        )
        val textLayoutResult = textMeasurer.measure(centerText, textStyle)
        val textWidth = textLayoutResult.size.width
        val textHeight = textLayoutResult.size.height

        drawText(
            textLayoutResult,
            topLeft = Offset(
                center.x - textWidth / 2,
                center.y - textHeight / 2
            )
        )
    }
}

private fun DrawScope.drawPieSlice(
    startAngle: Float,
    sweepAngle: Float,
    color: Color
) {
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = true
    )
}

@Composable
fun MyPieChart(data: Map<Color, Float>, text: String) {
    val centerCircleColor = MaterialTheme.colorScheme.surface
    val centerText = text
    val color = MaterialTheme.colorScheme.inverseSurface

    PieChart(
        data = data,
        centerCircleColor = centerCircleColor,
        textColor = color,
        centerText = centerText,
        modifier = Modifier.size(220.dp)
    )
}