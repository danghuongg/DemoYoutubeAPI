package com.danghuong.demoyoutubeapi.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.danghuong.demoyoutubeapi.data.room.YouTubeDataBase
import androidx.room.Room
import com.danghuong.demoyoutubeapi.data.room.Dao


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreference(context: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideRoomDb(context: Application): YouTubeDataBase {
        return Room.databaseBuilder(context.applicationContext, YouTubeDataBase::class.java, "YOUTUBEDATABASE") //                .fallbackToDestructiveMigration()//phá huy toan bọ csdl va tao ra csdl moi
            .addMigrations(YouTubeDataBase.MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideMessageThreadDao(db: YouTubeDataBase): Dao {
        return db.dao()
    }


}
