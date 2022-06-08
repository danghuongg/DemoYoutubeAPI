package com.danghuong.demoyoutubeapi.data.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TopLevelComment(
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
    var snippet: TopLevelCommentSnippet
)
