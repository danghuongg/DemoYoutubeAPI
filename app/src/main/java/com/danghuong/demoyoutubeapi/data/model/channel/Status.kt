package com.danghuong.demoyoutubeapi.data.model.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Status (
    @SerializedName("privacyStatus")
    @Expose
    var privacyStatus: String,
    @SerializedName("isLinked")
    @Expose
    var isLinked:Boolean,
    @SerializedName("longUploadsStatus")
    @Expose
    var longUploadsStatus: String,
    @SerializedName("madeForKids")
    @Expose
    var madeForKids :Boolean
    )