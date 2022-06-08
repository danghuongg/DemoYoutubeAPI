package com.danghuong.demoyoutubeapi

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.common.Event
import com.danghuong.demoyoutubeapi.common.LiveEvent
import com.danghuong.demoyoutubeapi.common.TwiceEvent
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.channel.ChannelRoot
import com.danghuong.demoyoutubeapi.data.model.comment.CommentRoot
//import com.danghuong.demoyoutubeapi.data.model.history.History
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.data.model.search.SearchRoot
import com.danghuong.demoyoutubeapi.data.model.videos.VideoRoot
import com.danghuong.demoyoutubeapi.data.repository.YoutubeRepository
import com.danghuong.demoyoutubeapi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(var youtubeRepository: YoutubeRepository) :
    BaseViewModel() {
    var searchLiveData = MutableLiveData<SearchRoot>()
    var searchByRegionLiveData = MutableLiveData<SearchRoot>()
    var channelLiveData = MutableLiveData<ChannelRoot>()
    var listOfVideosQueryItemLiveData = MutableLiveData<VideoRoot>()
    var listOfVideosUseAwaitQueryItemLiveData = MutableLiveData<VideoRoot>()
    var commentRootLiveData = MutableLiveData<CommentRoot>()
    var listOfVideoItemLiveData = MutableLiveData<VideoRoot>()
//    var historyItemLiveData = MutableLiveData<History>()
    var videoItem: VideoItem? = null
    var videoOverview1: MutableList<VideoOverview> = mutableListOf()
    var videoOverview2: MutableList<VideoOverview> = mutableListOf()
    var videoOverViewLiveData = MutableLiveData<MutableList<VideoOverview>>()
    var videoOverViewFromTrendingLiveData = MutableLiveData<MutableList<VideoOverview>>()
    var videoItemFromVideoOverView = MutableLiveData<VideoItem>()
    var videoRoot: VideoRoot? = null
    var videoRoot1: VideoRoot? = null
    var searchRoot: SearchRoot? = null
    var videoIdLiveData = MutableLiveData<String>()
    var event = LiveEvent<Event>()
    var twiceEvent = LiveEvent<TwiceEvent>()

    fun getSearch(key: String, part: String, q: String, type: String, maxResults: Int) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run {
                Timber.e(throwable)
            }
        })) {
            searchLiveData.postValue(
                youtubeRepository.getVideoSearchFromAPI(
                    key,
                    part,
                    q,
                    type,
                    maxResults
                ).body()
            )
        }
    }

    @SuppressLint("BinaryOperationInTimber")
    fun getVideoOverViewFromVideoTrending() {
        val job: Job = viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run { Timber.e(throwable) }
        })) {

           videoRoot = youtubeRepository.getVideoListByRegionCodeFromAPI(Common.MAXRESULT, Common.KEY, Common.VN).body()
            videoItem= videoRoot?.items?.get(0)
            val unit0: Deferred<VideoOverview> = async {
               videoRoot?.items?.get(0)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit0.await())

            val unit1: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(1)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit1.await())

            val unit2: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(2)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit2.await())


            val unit3: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(3)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit3.await())
            val unit4: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(4)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit4.await())
            val unit5: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(5)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit5.await())
            val unit6: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(6)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit6.await())
            val unit7: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(7)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit7.await())
            val unit8: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(8)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit8.await())
            val unit9: Deferred<VideoOverview> = async {
                videoRoot?.items?.get(9)?.let { VideoOverview(null,null, it) }!!
            }
            videoOverview1.add(unit9.await())
            videoOverViewFromTrendingLiveData.postValue(videoOverview1)

        }
    }


    @SuppressLint("BinaryOperationInTimber")
    fun getVideoOverView(q: String) {

        val job: Job=viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run { Timber.e(throwable) }
        })) {

            searchRoot = youtubeRepository.getVideoSearchFromAPI(
                Common.KEY,
                Common.PART,
                q,
                Common.TYPE,
                Common.MAXRESULTSEARCH
            ).body()
            Timber.e("huongdt: 4" + q.toString())
            videoOverview2.clear()
            val unit1: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(1)
                    ?.let {
                        searchRoot?.searchItemList?.get(1)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(Common.KEY, it.videoId).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit1.await())

            val unit2: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(2)
                    ?.let {
                        searchRoot?.searchItemList?.get(2)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY, it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit2.await())


            val unit3: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(3)
                    ?.let {
                        searchRoot?.searchItemList?.get(3)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!

            }
            videoOverview2.add(unit3.await())
            val unit4: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(4)
                    ?.let {
                        searchRoot?.searchItemList?.get(4)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit4.await())
            val unit5: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(5)
                    ?.let {
                        searchRoot?.searchItemList?.get(5)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit5.await())
            val unit6: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(6)
                    ?.let {
                        searchRoot?.searchItemList?.get(6)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit6.await())
            val unit7: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(7)
                    ?.let {
                        searchRoot?.searchItemList?.get(7)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit7.await())
            val unit8: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(8)
                    ?.let {
                        searchRoot?.searchItemList?.get(8)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit8.await())
            val unit9: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(9)
                    ?.let {
                        searchRoot?.searchItemList?.get(9)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit9.await())

            val unit10: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(10)
                    ?.let {
                        searchRoot?.searchItemList?.get(10)?.id?.let {
                            youtubeRepository.getVideoItemFromAPI(
                                Common.KEY,
                                it.videoId
                            ).body()
                        }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) }
                    }!!
            }
            videoOverview2.add(unit10.await())
            Timber.e("huongdt: 5 " + videoOverview2.size)
            Timber.e("huongdt: 6 $videoOverview2")
            videoOverViewLiveData.postValue(videoOverview2)

        }
    }


    fun getSearchByRegion(key: String, part: String, maxResults: Int) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run {
                Timber.e(throwable)
            }
        })) {
            searchByRegionLiveData.postValue(
                youtubeRepository.getVideoSearchFromAPIByRegion(
                    key,
                    part,
                    maxResults
                )
            )
        }
    }

    fun getChanner(key: String, id: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run {
                Timber.e(throwable)
            }
        })) {

            channelLiveData.postValue(youtubeRepository.getChannelFromAPI(key, id).body())
        }
    }

    fun getVideoItem(key: String, id: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run {
                Timber.e(throwable)
            }
        })) {
            listOfVideoItemLiveData.postValue(youtubeRepository.getVideoItemFromAPI(key, id).body())
        }
    }

    fun getVideoRoot(maxResult: Int, key: String, regionCode: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run {
                Timber.e(throwable)
            }
        })) {
            val item = youtubeRepository.getVideoListByRegionCodeFromAPI(maxResult, key, regionCode)
            listOfVideosQueryItemLiveData.postValue(item.body())
        }
    }

    fun getCommentRoot(maxResult: Int, videoId: String, key: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run {
                Timber.e(throwable)
            }
        })) {

            val item = youtubeRepository.getCommentRootFromAPI(maxResult, videoId, key)
            commentRootLiveData.postValue(item.body())
        }
    }

}