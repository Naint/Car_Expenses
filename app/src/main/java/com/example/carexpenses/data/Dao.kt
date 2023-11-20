package com.example.carexpenses.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.carexpenses.data.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert
    fun insertCar(car: Car)

    @Delete
    fun deleteCar(car : Car)

    @Query("SELECT * FROM cars")
    fun getAllCars() : LiveData<List<Car>>


}