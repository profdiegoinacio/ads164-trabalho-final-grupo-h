package com.example.myapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.data.model.Problema

@Database(entities = [Problema::class], version = 1)
abstract class ProblemaDatabase : RoomDatabase() {
    abstract fun problemaDao(): ProblemaDao

    companion object {
        @Volatile private var INSTANCE: ProblemaDatabase? = null

        fun getDatabase(context: Context): ProblemaDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ProblemaDatabase::class.java,
                    "problema_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}