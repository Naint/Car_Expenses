package com.example.carexpenses.data.repository

import androidx.lifecycle.LiveData
import com.example.carexpenses.data.Dao.ExpenseDao
import com.example.carexpenses.data.Expense


class ExpenseRealization(private val expenseDao : ExpenseDao) : ExpenseRepository {

    override val allExpenses: LiveData<List<Expense>>
        get() = expenseDao.getAllExpense()

    override suspend fun insertExpense(expense: Expense, onSuccess: () -> Unit) {
        expenseDao.insert(expense)
        onSuccess()
    }

    override suspend fun deleteExpense(expense: Expense, onSuccess: () -> Unit) {
        expenseDao.delete(expense)
        onSuccess()
    }

}