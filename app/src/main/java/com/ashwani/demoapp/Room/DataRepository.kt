package com.ashwani.demoapp.Room

import android.content.Context
import androidx.room.Room

class DataRepository(context: Context) {
    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).build()

    private val dataDao: DataDao = database.dataDao()

    suspend fun insertData(data: Data) {
        dataDao.insertData(data)
    }

    suspend fun getAllData(): List<Data> {
        return dataDao.getAllData()
    }

    suspend fun deleteAllData() {
        dataDao.deleteAllData()
    }
}