package com.danghuong.demoyoutubeapi.data.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ViewerRating (
    @SerializedName("value")
    @Expose
    val value: String)
