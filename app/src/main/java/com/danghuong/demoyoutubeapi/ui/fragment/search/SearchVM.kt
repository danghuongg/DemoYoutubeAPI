package com.danghuong.demoyoutubeapi.ui.fragment.search

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.history.History
import com.danghuong.demoyoutubeapi.data.model.search.SearchRoot
import com.danghuong.demoyoutubeapi.data.model.videos.VideoRoot
import com.danghuong.demoyoutubeapi.data.repository.HistoryRepository
import com.danghuong.demoyoutubeapi.data.repository.YoutubeRepository
import com.danghuong.demoyoutubeapi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SearchVM @Inject constructor(var youtubeRepository: YoutubeRepository, var historyRepository: HistoryRepository):BaseViewModel() {
    var videoSearchInFragSearch = MutableLiveData<MutableList<VideoOverview>>()
    var videoRoot1: VideoRoot? = null
    var searchRoot: SearchRoot? = null
    var videoOverview: MutableList<VideoOverview> = mutableListOf()
    var videoOverviewSearch= MutableLiveData<MutableList<VideoOverview>>()
    var gethistoryListLDT= MutableLiveData<MutableList<History>>()
    var deleteHistoryLDT= MutableLiveData<Unit>()
    var deleteAllHistoryLDT= MutableLiveData<Unit>()
    var insertHistoryLDT= MutableLiveData<Unit>()


    fun getListHistory(){
        viewModelScope.launch (Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run { Timber.e(throwable) }
        })) {

            gethistoryListLDT.postValue( historyRepository.getListHistory())
        }
    }
    fun deleteHistory(history: History){
        viewModelScope.launch (Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run { Timber.e(throwable) }
        })) {

            deleteHistoryLDT.postValue( historyRepository.removeHistory(history))
        }
    }
    fun deleteAllHistory(){
        viewModelScope.launch (Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run { Timber.e(throwable) }
        })) {

            deleteAllHistoryLDT.postValue( historyRepository.removeAllHistory())
        }
    }
    fun addHistory(history: History){
        viewModelScope.launch (Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run { Timber.e(throwable) }
        })) {

            insertHistoryLDT.postValue( historyRepository.addHistory(history))
        }
    }


    @SuppressLint("BinaryOperationInTimber")
    fun getVideoOverViewInFragSearch(q: String) {

        val job: Job =viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler(fun(
            _: CoroutineContext,
            throwable: Throwable
        ) {
            run { Timber.e(throwable) }
        })) {
            searchRoot?.searchItemList?.clear()

            searchRoot = youtubeRepository.getVideoSearchFromAPI(
                Common.KEY,
                Common.PART,
                q,
                Common.TYPE,
                Common.MAXRESULTSEARCH
            ).body()

            videoOverview.clear()
            val unit0: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(0)
                    ?.let { searchRoot?.searchItemList?.get(0)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(Common.KEY, it.videoId).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }

            videoOverview.add(unit0.await())

            val unit1: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(1)
                    ?.let { searchRoot?.searchItemList?.get(1)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(Common.KEY, it.videoId).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }
            videoOverview.add(unit1.await())

            val unit2: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(2)
                    ?.let {searchRoot?.searchItemList?.get(2)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY, it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }
            videoOverview.add(unit2.await())


            val unit3: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(3)
                    ?.let { searchRoot?.searchItemList?.get(3)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY,
                            it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!

            }
            videoOverview.add(unit3.await())
            val unit4: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(4)
                    ?.let { searchRoot?.searchItemList?.get(4)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY,
                            it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }

            videoOverview.add(unit4.await())
            val unit5: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(5)
                    ?.let {searchRoot?.searchItemList?.get(5)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY,
                            it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }

            videoOverview.add(unit5.await())
            val unit6: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(6)
                    ?.let {searchRoot?.searchItemList?.get(6)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY,
                            it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }
            videoOverview.add(unit6.await())
            val unit7: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(7)
                    ?.let { searchRoot?.searchItemList?.get(7)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY,
                            it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }
            videoOverview.add(unit7.await())
            val unit8: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(8)
                    ?.let { searchRoot?.searchItemList?.get(8)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY,
                            it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }
            videoOverview.add(unit8.await())
            val unit9: Deferred<VideoOverview> = async {
                searchRoot?.searchItemList?.get(9)
                    ?.let {searchRoot?.searchItemList?.get(9)?.id?.let {
                        youtubeRepository.getVideoItemFromAPI(
                            Common.KEY,
                            it.videoId
                        ).body()
                    }?.items?.get(0)?.let { it1 -> VideoOverview(null, it, it1) } }!!
            }
            videoOverview.add(unit9.await())

            Timber.e("huongdt: 5 "+videoOverview.size)
            Timber.e("huongdt: 6 $videoOverview")
            videoSearchInFragSearch.postValue(videoOverview)

        }
    }


}