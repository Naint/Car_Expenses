package com.example.carexpenses.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.carexpenses.CAR_REPOSITORY
import com.example.carexpenses.EXPENSE_REPOSITORY
import com.example.carexpenses.REFILL_REPOSITORY
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.MainDB
import com.example.carexpenses.data.Refill
import com.example.carexpenses.data.repository.CarRealization
import com.example.carexpenses.data.repository.ExpenseRealization
import com.example.carexpenses.data.repository.FuelRealization
import com.example.carexpenses.data.repository.FuelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FuelViewModel(application: Application) : AndroidViewModel(application) {


    val context = application

    fun initTable(){
        val refillDao = MainDB.getDatabase(context).refillDao()
        REFILL_REPOSITORY = FuelRealization(refillDao)
    }


    fun getAllFuel() : LiveData<List<Refill>> {
        return REFILL_REPOSITORY.allFuels
    }

    fun insert(refill: Refill, OnSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REFILL_REPOSITORY.insertRefill(refill){
            }
        }



}