package com.example.fintrack.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ExpenseDao {

    @Insert
    suspend fun insert(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("DELETE FROM expense")
    suspend fun deleteAll()

    @Query("SELECT * FROM expense WHERE date >= :weekAgo")
    fun getExpensesLastWeek(weekAgo: Long): Flow<List<Expense>>

    @Query("SELECT * FROM expense WHERE date >= :monthAgo")
    fun getExpensesLastMonth(monthAgo: Long): Flow<List<Expense>>

    @Query("SELECT * FROM expense WHERE date >= :yearAgo")
    fun getExpensesLastYear(yearAgo: Long): Flow<List<Expense>>

    @Query("SELECT * FROM expense ORDER BY date DESC, id ASC")
    fun getExpensesByTime(): Flow<List<Expense>>

    @Query("SELECT * FROM expense ORDER BY amount ASC")
    fun getExpensesSortedBySmallestAmount(): Flow<List<Expense>>

    @Query("SELECT * FROM expense ORDER BY amount DESC")
    fun getExpensesSortedByLargestAmount(): Flow<List<Expense>>
}
