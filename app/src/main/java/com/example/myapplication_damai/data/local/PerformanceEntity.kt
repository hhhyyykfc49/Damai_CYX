package com.example.myapplication_damai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "performance")
data class PerformanceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val city: String,

    val price: String
)