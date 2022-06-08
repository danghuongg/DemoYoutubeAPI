package com.danghuong.demoyoutubeapi.data.model.channel

import android.content.ClipData.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ChannelRoot(
    @SerializedName("kind")
    @Expose
    var kind: String,
    @SerializedName("etag")
    @Expose
    var etag: String,
    @SerializedName("pageInfo")
    @Expose
    var pageInfo: PageInfo,
    @SerializedName("items")
    @Expose
    var items: MutableList<ChannelItem>
)