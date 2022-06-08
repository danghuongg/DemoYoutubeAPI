package com.danghuong.demoyoutubeapi.data.model.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class History(
    @field: ColumnInfo var history: Int,
    @field: ColumnInfo var remove: Int,
    @field: ColumnInfo var videoName: String
) {
    @PrimaryKey(autoGenerate = true)
    var iVideo: Long? = null
}

