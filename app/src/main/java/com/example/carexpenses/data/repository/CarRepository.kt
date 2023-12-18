package com.example.carexpenses.data.repository

import androidx.lifecycle.LiveData
import com.example.carexpenses.data.Car

interface CarRepository {
    val allCars: LiveData<List<Car>>
    val selectedCars: LiveData<List<Car>>

    suspend fun insertCar(car : Car , onSuccess:() -> Unit)
    suspend fun deleteCar(car : Car , onSuccess:() -> Unit)
    suspend fun updateCar(car : Car , onSuccess:() -> Unit)
}