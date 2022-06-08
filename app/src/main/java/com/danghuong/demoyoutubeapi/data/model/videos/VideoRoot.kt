package com.danghuong.demoyoutubeapi.data.model.videos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope

class VideoRoot(
    @SerializedName("kind")
    @Expose
    var kind: String,
    @SerializedName("etag")
    @Expose
    var etag: String,
    @SerializedName("items")
    @Expose
    var items: MutableList<VideoItem>
) {


}

