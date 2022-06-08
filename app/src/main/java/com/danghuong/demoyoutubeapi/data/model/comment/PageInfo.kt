package com.danghuong.demoyoutubeapi.data.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PageInfo ( @SerializedName("totalResults")
                 @Expose
                 var totalResults : Int,
                 @SerializedName("resultsPerPage")
                 @Expose
                 var resultsPerPage : Int)
