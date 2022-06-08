package com.danghuong.demoyoutubeapi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.common.Event
import com.danghuong.demoyoutubeapi.ui.utils.Convert
import org.greenrobot.eventbus.EventBus

class NetworkReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        val status = context?.let { Convert.isOnline(it) }
        if (Common.CONNECTIVITY_CHANGE == intent?.action) {
            when (status) {
                Common.WIFI, Common.ETHERNET, Common.CELLPHONE -> {
//                    Handler(Looper.getMainLooper()).postDelayed({ }, 300)
                    EventBus.getDefault().post(Event( Common.Status_Network,true))
                   return
                }
                else -> {
                    EventBus.getDefault().post(Event( Common.Status_Network,false))
                    return
                }
            }
        }
    }


}

