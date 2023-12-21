package com.example.carexpenses.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "brand")
    var brand : String,
    @ColumnInfo(name = "model")
    var model : String,
    @ColumnInfo(name = "bodyType")
    var bodyType : String,
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

@Entity(tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Car::class,
            parentColumns = ["id"],
            childColumns = ["carId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "typeExpense")
    var typeExpense : String,
    @ColumnInfo(name = "mileage")
    var mileage : Int,
    @ColumnInfo(name = "date")
    var date : String,
    @ColumnInfo(name = "cost")
    var cost : Int,
    @ColumnInfo(name = "comment")
    var comment : String,
    @ColumnInfo(name = "carId")
    var carId : Int
)

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)

@Entity(tableName = "refill")
data class Refill(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "date")
    var date : String,
    @ColumnInfo(name = "mileage")
    var mileage : Int,
    @ColumnInfo(name = "price")
    var price : Double,
    @ColumnInfo(name = "fuelType")
    var fuelType : String,
    @ColumnInfo(name = "capacity")
    var capacity : Int,
    @ColumnInfo(name = "pricePerLiter")
    var pricePerLiter : Double,
    @ColumnInfo(name = "comment")
    var comment : String,
    @ColumnInfo(name = "carId")
    var carId : Int
)

