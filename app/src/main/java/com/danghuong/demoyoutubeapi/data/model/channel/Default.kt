package com.danghuong.demoyoutubeapi.data.model.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Default(
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("width")
    @Expose
    var width: Int,
    @SerializedName("height")
    @Expose
    var height: Int
)

