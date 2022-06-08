package com.danghuong.demoyoutubeapi.service

import android.app.NotificationChannel
import android.app.Service
import android.content.Intent
import android.os.IBinder

class DemoService : Service() {

    override fun onCreate() {
        super.onCreate()
//        setUpNotification("")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //time change rêcivẻ
        //time thay đổit hì upodate nội dung notìication


        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
            return null
    }

//    private fun setUpNotification(contentText: String) {
//        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel(
//                Constant.CHANNEL_ID,
//                "Channel human readable title",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            channel.setShowBadge(false)
//            channel.setSound(null, null)
//            notificationManager.createNotificationChannel(channel)
//        }
//        val intent1 = Intent(this, SplashActivity::class.java)
//        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        val pendingIntent: PendingIntent? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_IMMUTABLE)
//        } else {
//            PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//        val builder = NotificationCompat.Builder(context, Constant.CHANNEL_ID)
//        builder.setSmallIcon(R.drawable.icon_app_notification)
//        builder.setContentTitle(getString(R.string.app_name))
//        if (contentText.isNotEmpty()) {
//            builder.setContentText(contentText)
//        }
//        builder.setAutoCancel(false)
//        builder.setContentIntent(pendingIntent)
//        builder.setOngoing(true)
////        channelId.setDefaults(DEFAULT_VIBRATE)
////        channelId.priority = NotificationCompat.PRIORITY_HIGH
//        val notification = builder
//            .setChannelId(Constant.CHANNEL_ID)
//            .build()
//        startForeground(2001, notification)
//    }
}