package com.danghuong.demoyoutubeapi.ui.fragment.comment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.databinding.DialogCommentBinding
import com.danghuong.demoyoutubeapi.ui.adapter.CommentAdapter
import com.danghuong.demoyoutubeapi.ui.base.BaseBottomSheetDialogFragment
import timber.log.Timber

class Comment : BaseBottomSheetDialogFragment<DialogCommentBinding, CommentVM>() {
    var commentAdapter: CommentAdapter? = null
    var commentList: MutableList<VideoItem> = mutableListOf()
    var videoId: String? = null

    override val getLayoutId: Int
        get() = R.layout.dialog_comment

    override fun getViewModel(): Class<CommentVM> {
        return CommentVM::class.java
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreatedView(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()

    }

    private fun initData() {

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
    private fun initView() {

        initAdapter()
        initListener()
    }

    private fun initAdapter() {
        commentAdapter = CommentAdapter()
        binding.rcComment.adapter = commentAdapter

    }

    private fun initListener() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }


}


