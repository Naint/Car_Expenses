package com.example.carexpenses.screens.main

import android.app.Application
import androidx.lifecycle.*
import com.example.carexpenses.CAR_REPOSITORY
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.MainDB
import com.example.carexpenses.data.repository.CarRealization
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDataBase(){
        val carDao = MainDB.getDatabase(context).carDao()
        CAR_REPOSITORY = CarRealization(carDao)
    }

    fun getAllCars() : LiveData<List<Car>>{
        return CAR_REPOSITORY.allCars
    }

    fun getCurrentCars(): LiveData<List<Car>>{
        return CAR_REPOSITORY.currentCars
    }

    fun insert(car : Car, OnSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            CAR_REPOSITORY.insertCar(car){
            }
        }

    fun update(car : Car, OnSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            CAR_REPOSITORY.updateCar(car){
            }
        }
}