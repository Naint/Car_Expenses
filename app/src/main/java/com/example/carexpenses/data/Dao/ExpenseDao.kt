package com.example.carexpenses.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.carexpenses.data.Expense


@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expenses")
    fun getAllExpense(): LiveData<List<Expense>>
}