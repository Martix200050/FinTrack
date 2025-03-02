package com.example.fintrack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fintrack.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppinsbold)),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppinssemibold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppinsmedium)),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppinsregular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)