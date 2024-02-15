package com.example.carexpenses

import com.example.carexpenses.data.repository.CarRepository
import com.example.carexpenses.data.repository.ExpenseRepository
import com.example.carexpenses.data.repository.FuelRepository

lateinit var CAR_REPOSITORY : CarRepository
lateinit var EXPENSE_REPOSITORY : ExpenseRepository
lateinit var REFILL_REPOSITORY : FuelRepository

var SELECTED_CAR_ID: Int = 0