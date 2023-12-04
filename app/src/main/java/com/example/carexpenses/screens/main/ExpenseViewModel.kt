package com.example.carexpenses.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.carexpenses.CAR_REPOSITORY
import com.example.carexpenses.EXPENSE_REPOSITORY
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.Expense
import com.example.carexpenses.data.MainDB
import com.example.carexpenses.data.repository.ExpenseRealization
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initTable(){
        val expenseDao = MainDB.getDatabase(context).expenseDao()
        EXPENSE_REPOSITORY = ExpenseRealization(expenseDao)
    }

    fun getAllExpense() : LiveData<List<Expense>> {
        return EXPENSE_REPOSITORY.allExpenses
    }

    fun insert(expense: Expense, OnSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            EXPENSE_REPOSITORY.insertExpense(expense){

            }
        }

}