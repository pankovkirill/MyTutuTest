package com.tutu.myapplication.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("Select * from HistoryEntity")
    suspend fun all(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<HistoryEntity>)

    @Query("Delete From HistoryEntity")
    suspend fun delete()
}