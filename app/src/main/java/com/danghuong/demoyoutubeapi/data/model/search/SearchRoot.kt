package com.danghuong.demoyoutubeapi.data.model.search

import com.danghuong.demoyoutubeapi.data.model.channel.PageInfo
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchRoot(
    @SerializedName("kind")
    @Expose
    var kind: String,
    @SerializedName("etag")
    @Expose
    var etag: String,
    @SerializedName("nextPageToken")
    @Expose
    var nextPageToken: String,
    @SerializedName("regionCode")
    @Expose
    var regionCode: String,
    @SerializedName("pageInfo")
    @Expose
    var pageInfo: PageInfo,
    @SerializedName("items")
    @Expose
    var searchItemList: MutableList<SearchItem>
)
