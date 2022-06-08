package com.danghuong.demoyoutubeapi.ui.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.danghuong.demoyoutubeapi.App
import com.danghuong.demoyoutubeapi.MainActivity
import com.danghuong.demoyoutubeapi.common.Common
import java.util.*

object LocaleUtils {
    var codeLanguageCurrent = "null"
    val localeCompat: Locale
        get() {
            val configuration = Resources.getSystem().configuration
            return if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) configuration.locales[0] else configuration.locale
        }

    fun applyLocale(context: Context) {
        codeLanguageCurrent =
            App.mPrefs.getString(Common.PREF_SETTING_LANGUAGE, Common.LANGUAGE_EN).toString()
        if (TextUtils.isEmpty(codeLanguageCurrent)) {
            codeLanguageCurrent = Common.LANGUAGE_EN
        }
        val newLocale = Locale(codeLanguageCurrent)
        updateResource(context, newLocale)
        if (context !== context.applicationContext) {
            updateResource(context.applicationContext, newLocale)
        }
    }

    fun updateResource(context: Context, locale: Locale) {
        Locale.setDefault(locale)
        val resources = context.resources
        val current = getLocaleCompat(resources)
        if (current.language == locale.language) {
            return
        }
        val configuration = Configuration(resources.configuration)
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            configuration.setLocale(locale)
        } else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            configuration.setLocale(locale)
        } else {
            configuration.locale = locale
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    fun getLocaleCompat(resources: Resources): Locale {
        val configuration = resources.configuration
        return if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) configuration.locales[0] else configuration.locale
    }

    fun applyLocaleAndRestart(activity: Activity, localeString: String) {
        val current = getLocaleCompat(activity.resources)
        if (current.language != localeString) {
            App.mPrefs.edit().putString(Common.PREF_SETTING_LANGUAGE, localeString)
                .apply()


            applyLocale(activity)


            //restart
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
            ActivityCompat.finishAffinity(activity)
        }
    }
}