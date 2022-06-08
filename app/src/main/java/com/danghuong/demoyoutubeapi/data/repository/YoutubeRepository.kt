package com.danghuong.demoyoutubeapi.data.repository

import android.util.Log
import com.danghuong.demoyoutubeapi.data.model.channel.ChannelRoot
import com.danghuong.demoyoutubeapi.data.model.comment.CommentRoot
import com.danghuong.demoyoutubeapi.data.model.search.SearchRoot
import com.danghuong.demoyoutubeapi.data.model.videos.VideoRoot
import com.danghuong.demoyoutubeapi.remote.YoutubeRetrofit
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class YoutubeRepository @Inject constructor(var youtubeRetrofit: YoutubeRetrofit) {


    suspend fun getVideoSearchFromAPI(key: String,part: String,q: String,type: String,maxResults: Int): Response<SearchRoot>{
            return youtubeRetrofit.getVideoSearch(key,part,q,type,maxResults)
    }
    fun getVideoSearchFromAPIs(key: String,part: String,q: String,type: String,maxResults: Int): Response<SearchRoot>{
            return youtubeRetrofit.getVideoSearchs(key,part,q,type,maxResults)
    }
    fun getVideoSearchFromAPIByRegion(key: String,part: String,maxResults: Int): SearchRoot{
            return youtubeRetrofit.getVideoSearchByRegion(key,part,maxResults)
    }
    suspend fun getChannelFromAPI( key: String,id:String): Response<ChannelRoot>{
        return youtubeRetrofit.getChannel(key,id)
    }
    suspend fun getVideoListByRegionCodeFromAPI(maxResult: Int, key: String, regionCode: String) : Response<VideoRoot> {
          Timber.e("huongdt: getvideo "+youtubeRetrofit.getVideoListByRegionCode(maxResult, key, regionCode).toString())
        return youtubeRetrofit.getVideoListByRegionCode(maxResult, key, regionCode)
    }
    suspend fun getCommentRootFromAPI(maxResult: Int, videoId: String, key: String) : Response<CommentRoot> {
          Timber.e("huongdt: "+youtubeRetrofit.getCommentYoutube(maxResult, videoId, key).toString())
        return youtubeRetrofit.getCommentYoutube(maxResult, videoId, key)
    }
    suspend fun getVideoItemFromAPI(key: String,id: String) :Response<VideoRoot>{
        return youtubeRetrofit.getVideoList(key,id)
    }

}