package com.example.myapplication_damai.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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

    @Query("DELETE FROM performance")
    suspend fun clearAll()
}

@Dao
interface  MyOrderPerformanceDao{
    @Query(
        """
        SELECT * FROM myOrderPerformance
        ORDER BY id 
        """
    )
    fun getAllHistory(): Flow<List<MyOrderPerformanceEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        entity: MyOrderPerformanceEntity
    )

    @Query(
        """
        SELECT * FROM myOrderPerformance
        WHERE idCode = :idCode
        LIMIT 1
        """
    )
    suspend fun findByIdCode(
        idCode: Int
    ): MyOrderPerformanceEntity?

    //演示用
    @Query("DELETE FROM myOrderPerformance")
    suspend fun clearAll()
}