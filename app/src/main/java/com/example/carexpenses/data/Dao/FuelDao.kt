package com.example.carexpenses.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.Expense
import com.example.carexpenses.data.Refill

@Dao
interface FuelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(refill: Refill)

    @Delete
    suspend fun delete(refill: Refill)

    @Update
    suspend fun update(refill: Refill)

    @Query("SELECT * FROM refill")
    fun getAllFuel(): LiveData<List<Refill>>
}