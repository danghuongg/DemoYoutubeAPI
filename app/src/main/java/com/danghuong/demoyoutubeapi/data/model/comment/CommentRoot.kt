package com.danghuong.demoyoutubeapi.data.model.comment

import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentRoot(
    @SerializedName("kind")
    @Expose
    var kind: String,
    @SerializedName("etag")
    @Expose
    var etag: String,
    @SerializedName("nextPageToken")
    @Expose
    var nextPageToken: String,
    @SerializedName("pageInfo")
    @Expose
    var pageInfo: PageInfo,
    @SerializedName("items")
    @Expose
    var items: List<VideoItem>?=null
)
