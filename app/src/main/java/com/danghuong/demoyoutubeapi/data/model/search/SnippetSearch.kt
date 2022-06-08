package com.danghuong.demoyoutubeapi.data.model.search

import com.danghuong.demoyoutubeapi.data.model.channel.Localized
import com.danghuong.demoyoutubeapi.data.model.comment.TopLevelComment
import com.danghuong.demoyoutubeapi.data.model.comment.TopLevelCommentSnippet
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SnippetSearch(
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String,
    @SerializedName("channelId")
    @Expose
    var channelId: String,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("thumbnails")
    @Expose
    var thumbnails: Thumbnails,
    @SerializedName("channelTitle")
    @Expose
    var channelTitle: String,
    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null,
    @SerializedName("categoryId")
    @Expose
    var categoryId: String,
    @SerializedName("liveBroadcastContent")
    @Expose
    var liveBroadcastContent: String,
    @SerializedName("localized")
    @Expose
    var localized: Localized,
    @SerializedName("snippet")
    @Expose
    var snippet: TopLevelCommentSnippet,
    @SerializedName("topLevelComment")
    @Expose
    val topLevelComment: TopLevelComment

)