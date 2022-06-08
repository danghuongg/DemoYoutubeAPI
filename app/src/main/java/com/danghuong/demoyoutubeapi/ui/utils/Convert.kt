package com.danghuong.demoyoutubeapi.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.data.model.channel.ChannelItem
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.ui.fragment.comment.Comment
import java.time.OffsetDateTime
import java.util.*

class Convert() {
    companion object {
        @SuppressLint("SetTextI18n")
        fun convertCommentView(videoYoutube: VideoItem): String {
            var textView: String = ""
            val comment: String = videoYoutube.statisticVideoItem.commentCount
            textView = if (comment == null) {
                "0"
            } else {
                val likeCount: Long =
                    videoYoutube.statisticVideoItem.commentCount.toInt().toLong()
                if (likeCount < 1000) {
                    likeCount.toString()
                } else if (likeCount / 1000 < 1000) {
                    (likeCount / 1000).toString() + "K"
                } else if (likeCount / 1000000 in 1..999) {
                    (likeCount / 1000000).toString() + "M"
                } else {
                    (likeCount / 1000000000).toString() + "B"
                }
            }
            return textView
        }

        @SuppressLint("SetTextI18n")
        fun convertDuration(time: Long): String {
            var duration = "01:01:00"
            if (time in 1..9) {
                duration = "00:0$time"
            }
            if (time in 10..59) {
                duration = "00:$time"
            }
            if (time in 60..599) {
                duration = if (time % 60 < 10) {
                    "0" + time / 60 + ":0" + time % 60
                } else "0" + time / 60 + ":" + time % 60
            }
            if (time in 600..3599) {
                duration = if (time % 60 < 10) {
                    (time / 60).toString() + "0:" + time % 60
                } else {
                    (time / 60).toString() + ":" + time % 60
                }
            }
            if (time in 3600..35999) {
                if (time % 60 < 10 && time % 3600 / 60 < 10) {
                    duration = "0" + time / 3600 + "0:" + time % 3600 / 60 + "0:" + time % 60
                }
                if (time % 60 >= 10 && time % 3600 / 60 < 10) {
                    duration = "0:" + time / 3600 + "0:" + time % 3600 / 60 + ":" + time % 60
                }
                if (time % 60 < 10 && time % 3600 / 60 >= 10) {
                    duration = "0:" + time / 3600 + ":" + time % 3600 / 60 + "0:" + time % 60
                }
                if (time % 60 >= 10 && time % 3600 / 60 >= 10) {
                    duration = (time / 3600).toString() + ":" + time % 3600 / 60 + ":" + time % 60
                }
            }
            if (time >= 36000) {
                if (time % 60 < 10 && time % 3600 / 60 < 10) {
                    duration = (time / 3600).toString() + "0:" + time % 3600 / 60 + "0:" + time % 60
                }
                if (time % 60 >= 10 && time % 3600 / 60 < 10) {
                    duration = (+time / 3600).toString() + "0:" + time % 3600 / 60 + ":" + time % 60
                }
                if (time % 60 < 10 && time % 3600 / 60 >= 10) {
                    duration = (+time / 3600).toString() + ":" + time % 3600 / 60 + "0:" + time % 60
                }
                if (time % 60 >= 10 && time % 3600 / 60 >= 10) {
                    duration = (time / 3600).toString() + ":" + time % 3600 / 60 + ":" + time % 60
                }
            }
            return duration
        }

        @SuppressLint("SetTextI18n")
        fun convertViewCount(context: Context, view: String): String {
            var textView = ""
            val viewCount = view.toLong()
            if (viewCount < 1000) {
                textView = "$viewCount views"
            }
            if (viewCount in 1000..999999) {
                textView = "${viewCount / 1000}" + context.getString(R.string.view_count)
            }
            if (viewCount in 1000000..999999999) {
                textView = "${viewCount / 1000000}" + context.getString(R.string.view_count_m)
            }
            if (viewCount >= 1000000000) {
                textView = "${viewCount / 1000000000}" + context.getString(R.string.view_count_b)
            }
            return textView
        }

        @SuppressLint("SetTextI18n")
        fun convertPublishday(myDate: String): String {
            var time: String = ""
            val today = Calendar.getInstance()
            //        String myDate = item.getSnippet().getPublishedAt();
            var inputModified = myDate.replace(" ", "T")
            val lengthOfAbbreviatedOffset = 3
            if (inputModified.indexOf("+") == inputModified.length - lengthOfAbbreviatedOffset) {
                // If third character from end is a PLUS SIGN, append ':00'.
                inputModified = "$inputModified:00"
            }
            if (inputModified.indexOf("-") == inputModified.length - lengthOfAbbreviatedOffset) {
                // If third character from end is a PLUS SIGN, append ':00'.
                inputModified = "$inputModified:00"
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val odt = OffsetDateTime.parse(inputModified)
                val millis = odt
                    .toInstant().toEpochMilli()
                val diff = today.timeInMillis - millis
                val days = diff / (24 * 60 * 60 * 1000)
                val year = days / 365
                val month = (days - year * 365) / 30
                val day = days - year * 365 - month * 30
                val hour = diff / (60 * 60 * 1000) - year * 365 * 24 - month * 30 * 24 - day * 24
                val minute =
                    diff / (60 * 1000) - year * 365 * 24 * 60 - month * 30 * 24 * 60 - day * 24 * 60 - hour * 60
                if (year > 0) {
                    time = "$year năm trước"
                } else if (month > 0 && year == 0L) {
                    time = "$month tháng trước"
                } else if (day > 0 && month == 0L && year == 0L) {
                    time = "$day ngày trước"
                } else if (hour > 0 && day == 0L && month == 0L && year == 0L) {
                    time = "$hour giờ trước"
                } else if (minute > 0 && hour == 0L && day == 0L && month == 0L && year == 0L) {
                    time = "$minute phút trước"
                }
            }
            return time
        }

        @SuppressLint("SetTextI18n")
        fun convertSub(items: ChannelItem): String {
            var textView: String = ""
            val view: String = items.statistics.subscriberCount.toString()
            val viewCount = view.toLong()
            if (viewCount in 1000..999999) {
                textView = (viewCount / 1000).toString() + " " + "nghìn đăng ký"
            }
            if (viewCount in 1000000..999999999) {
                textView = (viewCount / 1000000).toString() + " " + "triệu đăng ký"
            }
            if (viewCount >= 1000000000) {
                textView = (viewCount / 1000000000).toString() + " " + "tỷ đăng ký"
            }
            return textView
        }

        @SuppressLint("SetTextI18n")
        fun convertLikeCount(items: VideoItem): String {
            var textView: String = ""
            val like: String = items.statisticVideoItem.likeCount
            if (items != null) {
                val likeCount = like.toInt().toLong()
                textView = if (likeCount < 1000) {
                    likeCount.toString() + ""
                } else if (likeCount / 1000 < 1000) {
                    (likeCount / 1000).toString() + "K"
                } else if (likeCount / 1000000 in 1..999) {
                    (likeCount / 1000000).toString() + "M"
                } else {
                    (likeCount / 1000000000).toString() + "B"
                }
            }
            return textView
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun isOnline(context: Context): Int {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return Common.CELLPHONE
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return Common.WIFI
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return Common.ETHERNET
                }
            }
            return Common.DISCONNECTED
        }
    }


}
