package com.example.myapplication_damai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val keyword: String,

    val createTime: Long = System.currentTimeMillis()
)