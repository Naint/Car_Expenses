package com.example.carexpenses.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carexpenses.data.Car
import com.example.carexpenses.data.Dao.ExpenseDao
import com.example.carexpenses.data.Dao.FuelDao

@Database(entities = [Car::class, Expense::class, Refill::class], version = 1)
abstract class MainDB: RoomDatabase() {

    abstract fun carDao(): CarDao
    abstract fun expenseDao() : ExpenseDao
    abstract fun refillDao() : FuelDao

    companion object {
        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDatabase(context: Context): MainDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDB::class.java,
                    "mains_1.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}