package com.danghuong.demoyoutubeapi.ui.fragment.search

//import com.danghuong.demoyoutubeapi.data.model.history.History
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.danghuong.demoyoutubeapi.MainActivity
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.history.History
import com.danghuong.demoyoutubeapi.databinding.FragSearchRcBinding
import com.danghuong.demoyoutubeapi.ui.adapter.VideoFingerOutAdapter
import com.danghuong.demoyoutubeapi.ui.adapter.VideoHistoryAdapter
import com.danghuong.demoyoutubeapi.ui.adapter.VideoSearchAdapter
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingFragment
import java.util.*

class FragSearch : BaseBindingFragment<FragSearchRcBinding, SearchVM>() {
    var videoSearchAdapter: VideoSearchAdapter? = null
    var videoFingerOutAdapter: VideoFingerOutAdapter? = null
    var videoHistoryAdapter: VideoHistoryAdapter? = null
    var videoSearch1: MutableList<VideoOverview> = mutableListOf()
    var videoSearch2: MutableList<VideoOverview> = mutableListOf()
    var videoSearch3: MutableList<History> = mutableListOf()
    var videoSearch4: MutableList<History> = mutableListOf()
    var query: String? = null
    var launcherVoice = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            if (data != null) {
                val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                binding.ivSearchview.setQuery(results?.get(0) , true)
            }
        }
    }

    override val getLayoutId: Int
        get() = R.layout.frag_search_rc

    override fun getViewModel(): Class<SearchVM> {
        return SearchVM::class.java
    }

    override fun onPermissionGranted() {
    }

    override fun onCreatedView(view: View, savedInstaceState: Bundle?) {
        binding.ivSearchview.requestFocus()
        binding.ivSearchview.isIconified = false
        binding.ivSearchview.queryHint = Common.Search
        initData()
        initView()
    }

    private fun initData() {
        viewModel.getListHistory()
        viewModel.gethistoryListLDT.observe(viewLifecycleOwner) {
            videoSearch3.clear()
            videoSearch3.addAll(it)
            videoSearch3.reverse()
            videoHistoryAdapter?.videoItemClick = object : VideoHistoryAdapter.VideoItemClick {
                override fun onClick(position: Int) {
                    binding.ivSearchview.setQuery(videoSearch3[position].videoName.toString(), true)
                }
            }
            videoHistoryAdapter?.setVideoHistoryList(videoSearch3)
        }

        viewModel.videoSearchInFragSearch.observe(viewLifecycleOwner) {
            videoSearch1.clear()
            videoSearch1.addAll(it)
            videoSearchAdapter?.videoItemClick = object : VideoSearchAdapter.VideoItemClick {
                override fun onClick(position: Int) {
                    binding.ivSearchview.setQuery(
                        videoSearch1[position].videoItem.snippetVideoItem.title,
                        true
                    )
                }
            }
            videoSearchAdapter?.setVideoSearchList(videoSearch1)
            videoSearch2.clear()
            videoSearch2.addAll(it)
            videoFingerOutAdapter?.videoItemClick = object : VideoFingerOutAdapter.VideoItemClick {
                override fun onClick(position: Int) {
                    (requireActivity() as MainActivity).navControllerMain?.navigate(R.id.frag_play_or_pause)
                    mainViewModel.videoItemFromVideoOverView.postValue(videoSearch2[position].videoItem)
                }
            }
            videoFingerOutAdapter?.setVideoFingerOutList(videoSearch2)
        }
    }

    private fun initView() {
        initListener()
        initAdapter()
    }

    private fun initAdapter() {
        videoSearchAdapter = VideoSearchAdapter()
        binding.rcVideoSearch.adapter = videoSearchAdapter

        videoHistoryAdapter = VideoHistoryAdapter()
        binding.rcVideoHistory.adapter = videoHistoryAdapter

        videoFingerOutAdapter = VideoFingerOutAdapter()
        binding.rcVideoFingureOut.adapter = videoFingerOutAdapter

    }

    fun initListener() {
        binding.ivSearchview.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            // Được kích hoạt khi nhấn tìm kiếm
            override fun onQueryTextSubmit(query: String): Boolean {
                val history = History(
                    R.drawable.ic_baseline_history_24,
                    R.drawable.custom_remove_history,
                    query
                )
                if (query.isNotEmpty()) {

                    checkVisible(View.GONE, View.GONE, View.VISIBLE)
                    viewModel.gethistoryListLDT.value?.let { videoSearch4.addAll(it) }

                    for (i in videoSearch4.indices) {
                        if (videoSearch4[i].videoName != history.videoName) {
                            viewModel.deleteHistory(history)
                        }
                    }
                    viewModel.addHistory(history)
                }
                else {
                    checkVisible(View.GONE, View.VISIBLE, View.GONE)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // được gọi khi dùng nhập từng ký tự vào trường
                if (newText.isNotEmpty()) {
                    videoSearch1.clear()
                    binding.tvHistory.visibility=View.INVISIBLE
                    viewModel.getVideoOverViewInFragSearch(newText)
                    checkVisible(View.VISIBLE, View.GONE, View.GONE)
//                    viewModel.videoSearchInFragSearch.postValue(videoSearch1)

                } else {
                    binding.tvHistory.visibility=View.VISIBLE
                    checkVisible(View.GONE, View.VISIBLE, View.GONE)
                }

                return false
            }
        })

        binding.imgVoice.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            launcherVoice.launch(intent)
        }
    }

    fun checkVisible(a: Int, b: Int, c: Int) {
        binding.rcVideoSearch.visibility = a
        binding.rcVideoHistory.visibility = b
        binding.rcVideoFingureOut.visibility = c
    }
}









