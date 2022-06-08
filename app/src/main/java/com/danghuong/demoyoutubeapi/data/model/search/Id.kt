package com.danghuong.demoyoutubeapi.data.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Id (@SerializedName("kind")
          @Expose
          var kind: String,
          @SerializedName("videoId")
          @Expose
          var videoId: String)