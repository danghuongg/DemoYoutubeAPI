package com.danghuong.demoyoutubeapi.data.model.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Snippet(
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("customUrl")
    @Expose
    var customUrl: String,
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: Date,
    @SerializedName("thumbnails")
    @Expose
    var thumbnails: Thumbnails,
    @SerializedName("localized")
    @Expose
    var localized: Localized,
    @SerializedName("country")
    @Expose
    var country: String
)
