package com.danghuong.demoyoutubeapi.data.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TopLevelCommentSnippet(
    @SerializedName("videoId")
    @Expose
    var videoId: String,
    @SerializedName("textDisplay")
    @Expose
    var textDisplay: String,
    @SerializedName("textOriginal")
    @Expose
    var textOriginal: String,
    @SerializedName("authorDisplayName")
    @Expose
    var authorDisplayName: String,
    @SerializedName("authorProfileImageUrl")
    @Expose
    var authorProfileImageUrL: String,
    @SerializedName("authorChannelUrl")
    @Expose
    var authorChannelUrl: String,
    @SerializedName("authorChannelId")
    @Expose
    var authorChannelId: AuthorChannelId,
    @SerializedName("canRate")
    @Expose
    var canRate: Boolean,
    @SerializedName("viewerRating")
    @Expose
    var viewerRating: String,
    @SerializedName("likeCount")
    @Expose
    var likeCount: Int,
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String,
    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String
)
