package com.danghuong.demoyoutubeapi.data.model.videos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatisticVideoItem(
    @SerializedName("subscriberCount")
    @Expose
    var subscriberCount: String,

    @SerializedName("viewCount")
    @Expose
    var viewCount: String,
    @SerializedName("likeCount")
    @Expose
    var likeCount: String,
    @SerializedName("dislikeCount")
    @Expose
    var dislikeCount: String,
    @SerializedName("favoriteCount")
    @Expose
    var favoriteCount: String,
    @SerializedName("commentCount")
    @Expose
    var commentCount: String

)