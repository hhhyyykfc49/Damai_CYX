package com.example.myapplication_damai.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(
        context: Context
    ): AppDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance =
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "damai_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            INSTANCE = instance

            instance
        }
    }
}