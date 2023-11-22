package com.example.carexpenses.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "brand")
    var brand : String,
    @ColumnInfo(name = "model")
    var model : String,
    @ColumnInfo(name = "createYear")
    var createYear : Int,
    @ColumnInfo(name = "mileage")
    var mileage : Int,
    @ColumnInfo(name = "vin")
    var vin : String,
    @ColumnInfo(name = "fuelType")
    var fuelType : String,
    @ColumnInfo(name = "otherInfo")
    var otherInfo : String,
    @ColumnInfo(name = "selectedCar")
    var selectedCar : Boolean
)

/*
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "typeExpense")
    var typeExpense : String
)

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)

@Entity(tableName = "refill")
data class Refill(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)
*/

