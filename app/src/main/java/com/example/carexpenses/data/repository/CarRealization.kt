package com.example.carexpenses.data.repository

import androidx.lifecycle.LiveData
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.CarDao

class CarRealization(private val carDao : CarDao) : CarRepository {

    override val allCars: LiveData<List<Car>>
        get() = carDao.getAllCars()



    override suspend fun insertCar(car: Car, onSuccess: () -> Unit) {
        carDao.insert(car)
        onSuccess()
    }

    override suspend fun deleteCar(car: Car, onSuccess: () -> Unit) {
        carDao.delete(car)
        onSuccess()
    }

    override suspend fun updateCar(car: Car, onSuccess: () -> Unit) {
        carDao.update(car)
        onSuccess()
    }




}