package com.example.myapplication_damai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(
//    entities = [
//        SearchHistoryEntity::class
//    ],
//    version = 1,
//    exportSchema = false
//)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun searchHistoryDao(): SearchHistoryDao
//}
@Database(
    entities = [
        SearchHistoryEntity::class,
        PerformanceEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    abstract fun performanceDao(): PerformanceDao
}