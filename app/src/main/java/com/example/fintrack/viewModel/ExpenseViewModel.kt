package com.example.fintrack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.model.Expense
import com.example.fintrack.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    private val _expensesByDays = MutableStateFlow<List<Expense>>(emptyList())
    val expensesByDays: StateFlow<List<Expense>> = _expensesByDays

    init {
        viewModelScope.launch {
            repository.getExpensesByTime().collect { expenses ->
                _expenses.value = expenses
            }
        }
        viewModelScope.launch {
            repository.getExpensesLastMonth().collect { expenses ->
                _expensesByDays.value = expenses
            }
        }
    }

    fun getExpensesByWeek() {
        viewModelScope.launch {
            repository.getExpensesLastWeek().collect { expenses ->
                _expensesByDays.value = expenses
            }
        }
    }

    fun getExpensesByMonth() {
        viewModelScope.launch {
            repository.getExpensesLastMonth().collect { expenses ->
                _expensesByDays.value = expenses
            }
        }
    }

    fun getExpensesByYear() {
        viewModelScope.launch {
            repository.getExpensesLastYear().collect { expenses ->
                _expensesByDays.value = expenses
            }
        }
    }

    fun getExpensesByTime() {
        viewModelScope.launch {
            repository.getExpensesByTime().collect { expenses ->
                _expenses.value = expenses
            }
        }
    }

    fun getExpensesSortedBySmallestAmount() {
        viewModelScope.launch {
            repository.getExpensesSortedBySmallestAmount().collect { expenses ->
                _expenses.value = expenses
            }
        }
    }

    fun getExpensesSortedByLargestAmount() {
        viewModelScope.launch {
            repository.getExpensesSortedByLargestAmount().collect { expenses ->
                _expenses.value = expenses
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insert(expense)
        }
    }


    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.delete(expense)
        }
    }

    fun deleteAllExpenses() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}