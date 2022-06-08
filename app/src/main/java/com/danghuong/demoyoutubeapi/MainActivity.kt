package com.danghuong.demoyoutubeapi

import android.content.IntentFilter
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.databinding.ActivityMainBinding
import com.danghuong.demoyoutubeapi.receiver.NetworkReceiver
import com.danghuong.demoyoutubeapi.ui.adapter.MainActivityAdapter
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingActivity
import com.danghuong.demoyoutubeapi.ui.fragment.fragmain.FragMain
import timber.log.Timber

class MainActivity : BaseBindingActivity<ActivityMainBinding, MainViewModel>() {

    var navControllerMain: NavController? = null
    var navHostFragmentMain: NavHostFragment? = null
    var networkReceiver : NetworkReceiver?=null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setupView(savedInstanceState: Bundle?) {
        networkReceiver= NetworkReceiver()
        val intentFilter = IntentFilter(Common.CONNECTIVITY_CHANGE)
        registerReceiver(networkReceiver, intentFilter)
        navHostFragmentMain =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment_main) as NavHostFragment
        navControllerMain = navHostFragmentMain?.navController

    }

    override fun setupData() {
        viewModel.getVideoOverViewFromVideoTrending()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

}