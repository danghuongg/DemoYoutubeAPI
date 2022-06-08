package com.danghuong.demoyoutubeapi.data.model.videos

import com.danghuong.demoyoutubeapi.data.model.channel.Default
import com.danghuong.demoyoutubeapi.data.model.channel.High
import com.danghuong.demoyoutubeapi.data.model.channel.Medium
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThumbnailsVideo(
    @SerializedName("default")
    @Expose
    var default: Default,
    @SerializedName("medium")
    @Expose
    var medium: Medium,
    @SerializedName("high")
    @Expose
    var high: High
)

