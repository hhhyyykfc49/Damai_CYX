package com.example.myapplication_damai.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PerformanceDao {

    @Insert
    suspend fun insert(
        performance: PerformanceEntity
    )

    @Query("""
        SELECT *
        FROM performance
        WHERE title LIKE '%' || :keyword || '%'
    """)
    suspend fun searchPerformance(
        keyword: String
    ): List<PerformanceEntity>

}