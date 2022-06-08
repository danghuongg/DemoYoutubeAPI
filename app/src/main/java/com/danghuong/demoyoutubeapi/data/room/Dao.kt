package com.danghuong.demoyoutubeapi.data.room

import androidx.room.*
import androidx.room.Dao
import com.danghuong.demoyoutubeapi.data.model.history.History

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(history: History)


    @get:Query("SELECT*FROM History")
    val listHistory: MutableList<History>

    @Query("DELETE FROM History")
    suspend fun deleteAllSearchHistory()

    @Delete
    suspend fun deleteHistory(history: History)
}