package com.danghuong.demoyoutubeapi.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.danghuong.demoyoutubeapi.MainActivity
import com.danghuong.demoyoutubeapi.MainViewModel
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.common.Event
import com.danghuong.demoyoutubeapi.common.TwiceEvent
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.databinding.FragHomeRcBinding
import com.danghuong.demoyoutubeapi.ui.adapter.HomeAdapter
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingFragment
import timber.log.Timber

class FragHome : BaseBindingFragment<FragHomeRcBinding, HomeVM>() {
    var homeAdapter: HomeAdapter? = null
    private var listOfVideos: MutableList<VideoOverview> = mutableListOf()
    override val getLayoutId: Int
        get() = R.layout.frag_home_rc

    override fun getViewModel(): Class<HomeVM> {
        return HomeVM::class.java
    }

    override fun onPermissionGranted() {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreatedView(view: View, savedInstaceState: Bundle?) {
        initView()
        initData()
    }

    fun initData() {

        mainViewModel.videoOverViewFromTrendingLiveData.observe(viewLifecycleOwner) {
            listOfVideos.clear()
            listOfVideos.addAll(it)
            homeAdapter?.setListofvideos(listOfVideos)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.statusBarColor =
                resources.getColor(R.color.white, requireActivity().theme)
        } else {
            requireActivity().window.statusBarColor = resources.getColor(R.color.white)}
            setAdapter()
            initOnClick()


        }

        fun setAdapter() {
            homeAdapter = HomeAdapter()
            binding.rcHomeList.adapter = homeAdapter
            homeAdapter?.videoItemClick = object : HomeAdapter.VideoItemClick {
                @SuppressLint("LogNotTimber")
                override fun onClick(position: Int) {
                    (requireActivity() as MainActivity).navControllerMain?.navigate(R.id.frag_play_or_pause)
                    mainViewModel.videoItemFromVideoOverView.postValue(listOfVideos[position].videoItem)

                }
            }
        }

        private fun initOnClick() {

        }

    }





