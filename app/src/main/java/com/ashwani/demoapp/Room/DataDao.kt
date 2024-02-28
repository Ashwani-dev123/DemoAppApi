package com.ashwani.demoapp.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {
    @Insert
    suspend fun insertData(data: Data)

    @Query("SELECT * FROM data_table")
    suspend fun getAllData(): List<Data>

    @Query("DELETE FROM data_table")
    suspend fun deleteAllData()
}