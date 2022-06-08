package com.danghuong.demoyoutubeapi.data.model.videos
import com.danghuong.demoyoutubeapi.data.model.channel.ChannelRoot
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class VideoItem(
    @SerializedName("kind")
    @Expose
    var kind:String,
    @SerializedName("etag")
    @Expose
    var etag: String,
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("snippet")
    @Expose
    var snippetVideoItem: SnippetVideoItem,
    @SerializedName("contentDetails")
    @Expose
    var contentDetailVideo: ContentDetailVideo,
    @SerializedName("statistics")
    @Expose
    var statisticVideoItem: StatisticVideoItem,
//    var channel: ChannelRoot
)


