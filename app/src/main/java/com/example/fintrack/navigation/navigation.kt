package com.example.fintrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fintrack.view.AnalysisScreen
import com.example.fintrack.view.SettingsScreen
import com.example.fintrack.view.SpendingScreen
import com.example.fintrack.viewModel.ExpenseViewModel
import com.example.fintrack.viewModel.SettingsViewModel
import kotlinx.serialization.Serializable


@Composable
fun Navigation(expenseViewModel: ExpenseViewModel, settingsViewModel: SettingsViewModel) {


    val expenses = expenseViewModel.expenses.collectAsState()
    val expensesByDays = expenseViewModel.expensesByDays.collectAsState()
    val settings = settingsViewModel.isEnglishLanguage.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SpendingScreen
    ) {
        composable<SpendingScreen> {
            SpendingScreen(navController, settings.value, expenseViewModel, expenses.value)
        }
        composable<AnalysisScreen> {
            AnalysisScreen(navController, settings.value, expenseViewModel, expensesByDays.value)
        }
        composable<SettingsScreen> {
            SettingsScreen(navController, settings.value, settingsViewModel)
        }
    }
}

@Serializable
object SpendingScreen

@Serializable
object AnalysisScreen

@Serializable
object SettingsScreen