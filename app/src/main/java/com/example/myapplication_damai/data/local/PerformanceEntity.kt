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

@Entity(tableName = "myOrderPerformance")
data class MyOrderPerformanceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val city: String,

    val price: String,

    val time:String,

    val route:String,

    val image:Int,

    val idCode:Int,
)