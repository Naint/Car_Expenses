package com.example.carexpenses.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.carexpenses.data.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car : Car)

    @Delete
    suspend fun delete(car : Car)

    @Query("SELECT * FROM cars")
    fun getAllCars(): LiveData<List<Car>>


/*    @Query("SELECT * FROM cars")
    fun getAllCars() : List<Car>

    @Query("SELECT * FROM cars")
    fun getAllItem(): Flow<List<Car>>

    @Delete
    fun deleteCar(car : Car)

    @Update
    fun updateCar(car : Car)*/
}