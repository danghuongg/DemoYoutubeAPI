package com.danghuong.demoyoutubeapi.data.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import com.danghuong.demoyoutubeapi.MainActivity
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.databinding.ActivitySplashBinding
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseBindingActivity<ActivitySplashBinding, SplashVM>() {
    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun getViewModel(): Class<SplashVM> {
        return SplashVM::class.java
    }

    override fun setupView(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          window.statusBarColor =
                resources.getColor(android.R.color.holo_blue_dark, theme)
        } else {
          window.statusBarColor = resources.getColor(android.R.color.holo_blue_dark)}

        val handler = Handler(this.mainLooper)
        handler.postDelayed({ this.startActivity() }, 200);
    }

    private fun startActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setupData() {

    }
}


