package com.prempal.teachmintassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prempal.teachmintassignment.data.remote.response.MainResponse

@Dao
interface MainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataset: List<MainResponse.Item>)

    @Query("SELECT * FROM main_table")
    suspend fun fetchList(): List<MainResponse.Item>?

    @Query("DELETE FROM main_table")
    suspend fun deleteAllEntities()
}