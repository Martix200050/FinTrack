package com.example.fintrack.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class NavigationItems(
    val title: String,
    val icon: ImageVector,
)

val navItemsList = listOf(
    NavigationItems(
        title = "Spending",
        icon = Icons.Default.List,
    ),
    NavigationItems(
        title = "Analysis",
        icon = Icons.Default.Home,
    ),
    NavigationItems(
        title = "Settings",
        icon = Icons.Default.Settings,
    )
)

data class Category(
    val icon: ImageVector,
    val backgroundColor: Color,
    val mainColor: Color
)

val categoryItems = listOf(
    Category(
        icon = Icons.Default.Home,
        backgroundColor = Color(0xFF5D3F65),
        mainColor = Color(0xFFBFC4EB)
    ),
    Category(
        icon = Icons.Default.ShoppingCart,
        backgroundColor = Color(0xFF2A3F6D),
        mainColor = Color(0xFFA3B9DC)
    ),
    Category(
        icon = Icons.Default.Face,
        backgroundColor = Color(0xFF6F3F41),
        mainColor = Color(0xFFE6B9B2)
    ),
    Category(
        icon = Icons.Default.Place,
        backgroundColor = Color(0xFF3C5E51),
        mainColor = Color(0xFFA9C7B4)
    ),
)

data class PieChartData(
    val color: Color,
    val percentage: Float // Відсоток (наприклад, 30f для 30%)
)