package com.example.fintrack.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fintrack.model.Expense
import com.example.fintrack.model.categoryItems
import com.example.fintrack.model.navItemsList
import com.example.fintrack.navigation.AnalysisScreen
import com.example.fintrack.navigation.SettingsScreen
import com.example.fintrack.navigation.SpendingScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun BottomNavigationBar(navController: NavController, selectedIndex: Int) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(selectedIndex) }
    val thisIndex = selectedIndex
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        navItemsList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    when (item.title) {
                        "Spending" -> if (thisIndex != 0) navController.navigate(
                            SpendingScreen
                        )

                        "Analysis" -> if (thisIndex != 1) navController.navigate(
                            AnalysisScreen
                        )

                        "Settings" -> if (thisIndex != 2) navController.navigate(
                            SettingsScreen
                        )
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(34.dp),
                        imageVector = item.icon,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

fun millisecondsToTime(milliseconds: Long?, pattern: String = "dd.MM.yyyy"): String {
    if (milliseconds == null) return "No date"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(Date(milliseconds))
}

fun getPercentageOfAmountsWithCategory(expenses: List<Expense>): Map<Color, Float> {
    val amounts = mutableMapOf<Color, Float>(
        categoryItems[0].backgroundColor to 0f,
        categoryItems[1].backgroundColor to 0f,
        categoryItems[2].backgroundColor to 0f,
        categoryItems[3].backgroundColor to 0f
    )

    expenses.forEach { expense ->
        val color = categoryItems[expense.category].backgroundColor
        amounts[color] = amounts.getOrDefault(color, 0f) + expense.amount.toFloat()
    }
    val totalAmount = amounts.values.sum()
    if (totalAmount > 0) {
        amounts.replaceAll { _, value -> (value / totalAmount) * 100f }
    }
    return amounts
}

