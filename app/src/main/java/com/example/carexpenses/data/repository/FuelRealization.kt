package com.example.carexpenses.data.repository

import androidx.lifecycle.LiveData
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.CarDao
import com.example.carexpenses.data.Dao.FuelDao
import com.example.carexpenses.data.Refill

class FuelRealization(private val fuelDao : FuelDao) : FuelRepository {

    override val allFuels: LiveData<List<Refill>>
        get() = fuelDao.getAllFuel()


    override suspend fun insertRefill(refill: Refill, onSuccess: () -> Unit) {
        fuelDao.insert(refill)
        onSuccess()
    }

    override suspend fun deleteRefill(refill: Refill, onSuccess: () -> Unit) {
        fuelDao.delete(refill)
        onSuccess()
    }

    override suspend fun updateRefill(refill: Refill, onSuccess: () -> Unit) {
        fuelDao.update(refill)
        onSuccess()
    }

}