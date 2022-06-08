package com.danghuong.demoyoutubeapi.remote

import com.danghuong.demoyoutubeapi.data.model.channel.ChannelRoot
import com.danghuong.demoyoutubeapi.data.model.comment.CommentRoot
import com.danghuong.demoyoutubeapi.data.model.search.SearchRoot
import com.danghuong.demoyoutubeapi.data.model.videos.VideoRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeRetrofit {
    @GET("search/")
    suspend fun getVideoSearch(
        @Query("key") key: String, @Query("part") part: String, @Query("q") q: String,
        @Query("type") type: String, @Query("maxResults") maxResults: Int
    ): Response<SearchRoot>
    @GET("search/")
     fun getVideoSearchs(
        @Query("key") key: String, @Query("part") part: String, @Query("q") q: String,
        @Query("type") type: String, @Query("maxResults") maxResults: Int
    ): Response<SearchRoot>

    @GET("search?part=snippet&chart=mostPopular")
    fun getVideoSearchByRegion(
        @Query("key") key: String, @Query("part") part: String, @Query("maxResults") maxResults: Int
    ): SearchRoot

    @GET("channels?part=snippet&part=statistics")
    suspend fun getChannel(
        @Query("key") key: String,
        @Query("id") id: String
    ): Response<ChannelRoot>

    @GET("videos?part=snippet,contentDetails,statistics&chart=mostPopular")
    suspend fun getVideoListByRegionCode(
        @Query("maxResults") maxResults: Int,
        @Query("key") key: String,
        @Query("regionCode") regionCode: String
    ): Response<VideoRoot>

    @GET("commentThreads?part=snippet")
    suspend fun getCommentYoutube(
        @Query("maxResults") maxResults: Int,
        @Query("videoId") videoId: String,
        @Query("key") key: String
    ): Response<CommentRoot>

    @GET("videos?part=snippet&part=contentDetails&part=statistics")
   suspend fun getVideoList(@Query("key") key: String, @Query("id") id: String): Response<VideoRoot>


// Retrofit interface
//    package com.ayush.retrofitexample
//
//    import retrofit2.Response
//    import retrofit2.http.GET
//    import retrofit2.http.Query
//
//    interface QuotesApi {
//        @GET("/quotes")
//        suspend fun getQuotes() : Response<QuoteList>
//    }


}