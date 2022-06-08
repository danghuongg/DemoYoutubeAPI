package com.danghuong.demoyoutubeapi.data.model.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Statistics(
    @SerializedName("viewCount")
    @Expose
    var viewCount: Long,
    @SerializedName("subscriberCount")
    @Expose
    var subscriberCount: Long,
    @SerializedName("hiddenSubcribeCount")
    @Expose
    var hiddenSubcribeCount: Boolean,
    @SerializedName("videoCount")
    @Expose
    var videoCount : String
)

