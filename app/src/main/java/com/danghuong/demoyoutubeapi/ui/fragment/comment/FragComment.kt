package com.danghuong.demoyoutubeapi.ui.fragment.comment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.databinding.FragCommentBinding
import com.danghuong.demoyoutubeapi.ui.adapter.CommentAdapter
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingFragment
import timber.log.Timber

open class FragComment : BaseBindingFragment<FragCommentBinding, CommentVM>() {
    var commentAdapter: CommentAdapter? = null
    var commentList: MutableList<VideoItem> = mutableListOf()
    var videoId: String? = null
    override val getLayoutId: Int
        get() = R.layout.frag_comment


    override fun getViewModel(): Class<CommentVM> {
        return CommentVM::class.java
    }

    override fun onPermissionGranted() {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreatedView(view: View, savedInstaceState: Bundle?) {
        initView()
        initData()
    }

  fun initData() {

        mainViewModel.videoIdLiveData.observe(viewLifecycleOwner) {
            Timber.e("huongdt: videoId: $it")
            videoId = it

        }

        mainViewModel.commentRootLiveData.observe(viewLifecycleOwner) {
            Timber.e("huongdt: commentList: ${it.items?.size}")
            commentList.clear()
            it?.items?.let { it1 -> commentList.addAll(it1) }
            Timber.e("huongdt: commentList: ${it.items?.get(0)?.snippetVideoItem?.topLevelComment?.snippet?.textOriginal.toString()}")
            commentAdapter?.setListofComment(commentList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initView() {

//        binding.rcComment.isNestedScrollingEnabled = false;
        initAdapter()
        initListener()
    }

    fun initAdapter() {
        commentAdapter = CommentAdapter()
        binding.rcComment.adapter = commentAdapter

    }

    fun initListener() {
        binding.ivClose.setOnClickListener {

        }
    }
}