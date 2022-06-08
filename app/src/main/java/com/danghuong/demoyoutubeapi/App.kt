package com.danghuong.demoyoutubeapi

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.preference.PreferenceManager
import androidx.databinding.library.BuildConfig
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.danghuong.demoyoutubeapi.ui.base.BaseActivity
import com.danghuong.demoyoutubeapi.ui.utils.MyDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.*

@HiltAndroidApp
class App : MultiDexApplication() {
    lateinit var appContext: Context
    var WIDTH_SCREEN = 0
    var HEIGHT_SCREEN = 0
    var HEIGHT_STATUS_BAR = 0


    fun getContext(): Context {
        return appContext
    }

    fun getInstance(): App {
        return instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        initLog()
        appContext = applicationContext
        WIDTH_SCREEN = resources.displayMetrics.widthPixels
        HEIGHT_SCREEN = resources.displayMetrics.heightPixels

    }

    companion object {
        lateinit var instance: App
        lateinit var mPrefs: SharedPreferences
    }


    private fun initLog() {
        if (com.danghuong.demoyoutubeapi.BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
//        LocaleUtils.applyLocale(this)
    }


}