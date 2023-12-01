package com.example.carexpenses.data.repository

import androidx.lifecycle.LiveData
import com.example.carexpenses.data.Expense

interface ExpenseRepository {

    val allExpenses: LiveData<List<Expense>>

    suspend fun insertExpense(expense : Expense, onSuccess:() -> Unit)
    suspend fun deleteExpense(expense : Expense, onSuccess:() -> Unit)


}