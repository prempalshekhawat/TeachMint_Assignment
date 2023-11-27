package com.prempal.teachmintassignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prempal.teachmintassignment.data.local.MainDao
import com.prempal.teachmintassignment.data.remote.response.MainResponse

@Database(
    entities = [
        MainResponse.Item::class
    ],
    version = 2
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}