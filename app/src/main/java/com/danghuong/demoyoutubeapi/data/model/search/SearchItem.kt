package com.danghuong.demoyoutubeapi.data.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchItem(
    @SerializedName("kind")
    @Expose
    var kind: String,
    @SerializedName("etag")
    @Expose
    var etag: String,
    @SerializedName("id")
    @Expose
    var id: Id,
    @SerializedName("snippet")
    @Expose
    var snippetSearch: SnippetSearch
)
