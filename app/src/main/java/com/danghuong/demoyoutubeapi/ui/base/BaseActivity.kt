package com.danghuong.demoyoutubeapi.ui.base
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.ContextThemeWrapper
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.danghuong.demoyoutubeapi.common.Common
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}