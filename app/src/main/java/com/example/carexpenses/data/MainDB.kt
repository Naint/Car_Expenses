package com.example.carexpenses.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carexpenses.data.Car

@Database(entities = [Car::class], version = 1)
abstract class MainDB: RoomDatabase() {

    abstract fun carDao(): CarDao

/*    companion object{
        fun getDb(context: Context): MainDB {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "main.db"
            ).build()
        }
    }*/

    companion object {
        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDatabase(context: Context): MainDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDB::class.java,
                    "main.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}