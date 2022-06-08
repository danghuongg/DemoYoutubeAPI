package com.danghuong.demoyoutubeapi.data.model.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Localized(
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("description")
    @Expose
    var description: String
)

