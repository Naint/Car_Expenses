package com.example.carexpenses.data.repository

import androidx.lifecycle.LiveData
import com.example.carexpenses.data.Refill

interface FuelRepository {

    val allFuels: LiveData<List<Refill>>

    suspend fun insertRefill(refill: Refill, onSuccess:() -> Unit)
    suspend fun deleteRefill(refill: Refill, onSuccess:() -> Unit)
    suspend fun updateRefill(refill: Refill, onSuccess:() -> Unit)

}