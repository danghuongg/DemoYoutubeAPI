package com.danghuong.demoyoutubeapi.data.room
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.danghuong.demoyoutubeapi.data.model.history.History


@Database(entities = [History::class], version = 1,exportSchema = false)
 abstract class YouTubeDataBase :RoomDatabase(){
   open abstract fun dao(): Dao
    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}