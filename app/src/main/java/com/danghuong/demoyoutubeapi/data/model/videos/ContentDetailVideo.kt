package com.danghuong.demoyoutubeapi.data.model.videos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContentDetailVideo(
    @SerializedName("duration")
    @Expose
    var duration: String,
    @SerializedName("dimension")
    @Expose
    var dimension: String,
    @SerializedName("definition")
    @Expose
    private var definition: String,
    @SerializedName("caption")
    @Expose
    var caption: String,
    @SerializedName("licensedContent")
    @Expose
    private var licensedContent: Boolean,
//    @SerializedName("contentRating")
//    @Expose
//    private val contentRating: ContentRating,

    @SerializedName("projection")
    @Expose
    private val projection: String

)