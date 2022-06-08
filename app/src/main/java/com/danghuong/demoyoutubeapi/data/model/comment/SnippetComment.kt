package com.danghuong.demoyoutubeapi.data.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SnippetComment(
    @SerializedName("videoId")
    @Expose
    var videoId: String,

    @SerializedName("topLevelComment")
    @Expose
    var topLevelComment: TopLevelComment,
    @SerializedName("canReply")
    @Expose
    var canReply: Boolean,
    @SerializedName("totalReplyCount")
    @Expose
    var totalReplyCount: Long,
    @SerializedName("isPublic")
    @Expose
    var isPublic: Boolean
)
