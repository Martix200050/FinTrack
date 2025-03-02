package com.example.fintrack.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.model.AppDatabase
import com.example.fintrack.navigation.Navigation
import com.example.fintrack.repository.ExpenseRepository
import com.example.fintrack.viewModel.ExpenseViewModel
import com.example.fintrack.viewModel.ExpenseViewModelFactory
import com.example.fintrack.viewModel.SettingsViewModel

class MainActivity : ComponentActivity() {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsViewModel = SettingsViewModel(applicationContext)
        enableEdgeToEdge()
        setContent {
            val repository =
                ExpenseRepository(AppDatabase.getDatabase(applicationContext).expenseDao())
            expenseViewModel = ViewModelProvider(
                this,
                ExpenseViewModelFactory(repository)
            )[ExpenseViewModel::class.java]

            Navigation(expenseViewModel, settingsViewModel)
        }
    }
}