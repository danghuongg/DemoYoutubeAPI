package com.danghuong.demoyoutubeapi.data.repository

import com.danghuong.demoyoutubeapi.data.model.history.History
import com.danghuong.demoyoutubeapi.data.room.YouTubeDataBase
import javax.inject.Inject

class HistoryRepository @Inject constructor(val yoututeDataBase: YouTubeDataBase) {
    lateinit var historyItem: History

    suspend fun getListHistory(): MutableList<History> {
        return yoututeDataBase.dao().listHistory
    }

    suspend fun addHistory(historyItem: History) {
        return yoututeDataBase.dao().addHistory(historyItem)
    }

    suspend fun removeHistory(historyItem: History) {
        return yoututeDataBase.dao().deleteHistory(historyItem)
    }

    suspend fun removeAllHistory(){
        return yoututeDataBase.dao().deleteAllSearchHistory()
    }


}