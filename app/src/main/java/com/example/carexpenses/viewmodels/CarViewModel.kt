package com.example.carexpenses.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Entity
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.CarDao
import com.example.carexpenses.data.MainDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val carDao : CarDao
    private val allCars: LiveData<List<Car>>

    val context = application

    init{
        val db = MainDB.getDatabase(context)
        carDao = db.carDao()
        allCars = carDao.getAllCars()
    }

    fun insertCar(car: Car){
        viewModelScope.launch(Dispatchers.IO){
            carDao.insertCar(car)
        }
    }

}