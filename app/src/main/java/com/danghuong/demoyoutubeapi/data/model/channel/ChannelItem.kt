package com.danghuong.demoyoutubeapi.data.model.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChannelItem(
    @SerializedName("kind")
    @Expose
    var kind: String,
    @SerializedName("etag")
    @Expose
    var etag: String,
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("snippet")
    @Expose
    var snippet: Snippet,
    @SerializedName("statistics")
    @Expose
    var statistics: Statistics,
    @SerializedName("status")
    @Expose
    var status: Status
)

