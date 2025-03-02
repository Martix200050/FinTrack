package com.example.fintrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Int,
    val category: Int,
    val comment: String,
    val date: Long
)