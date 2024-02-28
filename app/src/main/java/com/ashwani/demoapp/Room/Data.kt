package com.ashwani.demoapp.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dataListJson: String // String representation of dataListMain
)