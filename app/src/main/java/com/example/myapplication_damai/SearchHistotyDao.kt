package com.example.myapplication_damai.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query(
        """
        SELECT * FROM search_history
        ORDER BY createTime DESC
        """
    )
    fun getAllHistory(): Flow<List<SearchHistoryEntity>>

    @Query(
        """
        SELECT * FROM search_history
        WHERE keyword = :keyword
        LIMIT 1
        """
    )
    suspend fun findByKeyword(
        keyword: String
    ): SearchHistoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        entity: SearchHistoryEntity
    )

    @Query(
        """
        DELETE FROM search_history
        WHERE id = :id
        """
    )
    suspend fun delete(
        id: Int
    )

    @Query("DELETE FROM search_history")
    suspend fun clearAll()
}