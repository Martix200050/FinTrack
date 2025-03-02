package com.example.fintrack.repository

import com.example.fintrack.model.Expense
import com.example.fintrack.model.ExpenseDao
import kotlinx.coroutines.flow.Flow


class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val currentTime = System.currentTimeMillis()

    suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    suspend fun delete(expense: Expense) {
        expenseDao.delete(expense)
    }

    suspend fun deleteAll() {
        expenseDao.deleteAll()
    }

    fun getExpensesLastWeek(): Flow<List<Expense>> {
        val weekAgo = currentTime - (7 * 24 * 60 * 60 * 1000)
        return expenseDao.getExpensesLastWeek(weekAgo)
    }

    fun getExpensesLastMonth(): Flow<List<Expense>> {
        val monthAgo = currentTime - (24 * 24 * 60 * 60 * 1000)
        return expenseDao.getExpensesLastMonth(monthAgo)
    }

    fun getExpensesLastYear(): Flow<List<Expense>> {
        val yearAgo = currentTime - (365 * 24 * 60 * 60 * 1000)
        return expenseDao.getExpensesLastYear(yearAgo)
    }

    fun getExpensesByTime(): Flow<List<Expense>> {
        return expenseDao.getExpensesByTime()
    }

    fun getExpensesSortedBySmallestAmount(): Flow<List<Expense>> {
        return expenseDao.getExpensesSortedBySmallestAmount()
    }

    fun getExpensesSortedByLargestAmount(): Flow<List<Expense>> {
        return expenseDao.getExpensesSortedByLargestAmount()
    }
}