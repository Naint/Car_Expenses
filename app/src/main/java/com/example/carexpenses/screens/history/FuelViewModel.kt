package com.example.carexpenses.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.carexpenses.REFILL_REPOSITORY
import com.example.carexpenses.data.MainDB
import com.example.carexpenses.data.Refill
import com.example.carexpenses.data.repository.FuelRealization
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