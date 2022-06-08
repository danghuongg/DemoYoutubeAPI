package com.danghuong.demoyoutubeapi.ui.fragment.playorpause

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.danghuong.demoyoutubeapi.MainActivity
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.databinding.FragPlayVideoBinding
import com.danghuong.demoyoutubeapi.ui.adapter.CommentAdapter
import com.danghuong.demoyoutubeapi.ui.adapter.OtherVideosAdapter
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingFragment
import com.danghuong.demoyoutubeapi.ui.fragment.comment.FragComment
import com.danghuong.demoyoutubeapi.ui.utils.Convert
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import timber.log.Timber

class FragPlayVideo : BaseBindingFragment<FragPlayVideoBinding, PlayVideoVM>() {
    var commentAdapter: CommentAdapter? = null
    var otherVideosAdapter: OtherVideosAdapter? = null
    var commentList: MutableList<VideoItem> = mutableListOf()
    var videoOverviewList: MutableList<VideoOverview> = mutableListOf()
    var videoOverviewListFromOther: MutableList<VideoOverview> = mutableListOf()
    var channelId: String = ""
    val fragcomment = FragComment()

    var youtubeListener: YouTubePlayer? = null
    private var position: Int = 0

    val listener: AbstractYouTubePlayerListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            youtubeListener = youTubePlayer
        }
    }
    var videoId: String? = null

    override val getLayoutId: Int
        get() = R.layout.frag_play_video

    override fun getViewModel(): Class<PlayVideoVM> {
        return PlayVideoVM::class.java
    }

    override fun onPermissionGranted() {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreatedView(view: View, savedInstaceState: Bundle?) {
        initData()
        initView()
        binding.youtube.addYouTubePlayerListener(listener)
    }

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.M)
    fun initView() {
        initAdapter()
        initListener()

    }

    private fun initAdapter() {
        commentAdapter = CommentAdapter()
        binding.rcComment.adapter = commentAdapter
        otherVideosAdapter = OtherVideosAdapter()
        binding.rcOthersVideo.adapter = otherVideosAdapter
        otherVideosAdapter?.videoItemClick = object : OtherVideosAdapter.VideoItemClick {
            @SuppressLint("LogNotTimber")
            override fun onClick(position: Int) {
                (requireActivity() as MainActivity).navControllerMain?.navigate(R.id.frag_play_or_pause)
                mainViewModel.videoItemFromVideoOverView.postValue(videoOverviewListFromOther[position].videoItem)
            }
        }
    }

    @SuppressLint("LogNotTimber", "SetTextI18n")
    fun initData() {
        mainViewModel.videoItemFromVideoOverView.observe(viewLifecycleOwner) {
            videoId = it.id
            channelId = it.snippetVideoItem.channelId

//            post videoID cho comment bottomsheet
            mainViewModel.videoIdLiveData.postValue(videoId)

            // get CommentRoot
            mainViewModel.getCommentRoot(Common.MAXCOMMENT, it.id, Common.KEY)

            //get channel root
            mainViewModel.getChanner(Common.KEY, it.snippetVideoItem.channelId)

            //UI
            binding.tvChannelName.text = it.snippetVideoItem.channelTitle
            binding.tvCommentNumber1.text =
                resources.getString(R.string.comment) + " - " + Convert.convertCommentView(it)

            binding.tvTitleVideo.text = it.snippetVideoItem.title

            //get query by title video
            mainViewModel.getVideoOverView(binding.tvTitleVideo.text.toString())
            Timber.e("huongdt: q: %s", binding.tvTitleVideo.text.toString())


            binding.tvLike.text = Convert.convertLikeCount(it)
            binding.tvViewcountAndDate.text = Convert.convertViewCount(
                requireActivity(), it.statisticVideoItem.viewCount
            ) + " - " + Convert.convertPublishday(it.snippetVideoItem.publishedAt)

            binding.youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                   youTubePlayer.loadVideo(it.id, 0F)
                }
            })

        }

        mainViewModel.channelLiveData.observe(viewLifecycleOwner) {
            //bởi vì chỉ lấy có 1 channel id nên trong danh sách các kênh ta chỉ có size=1 nên position luôn phải bằng 0
            binding.tvSub.text = Convert.convertSub(it.items[0])
            Glide.with(binding.ivChannelImage).load(it.items[0].snippet.thumbnails.high.url)
                .into(binding.ivChannelImage)
        }

        mainViewModel.commentRootLiveData.observe(viewLifecycleOwner) {
                Glide.with(binding.ivChannelImageComment)
                    .load(it.items?.get(0)?.snippetVideoItem?.topLevelComment?.snippet?.authorProfileImageUrL)
                    .into(binding.ivChannelImageComment)
                binding.tvComment.text =
                    it.items?.get(0)?.snippetVideoItem?.topLevelComment?.snippet?.textOriginal

                commentList.clear()
                it.items?.let { it1 -> commentList.addAll(it1) }
                commentAdapter?.setListofComment(commentList)
        }

        mainViewModel.videoOverViewLiveData.observe(viewLifecycleOwner) {
            it?.let {
                videoOverviewListFromOther.clear()
                videoOverviewListFromOther.addAll(it)
                otherVideosAdapter?.setOthersList(videoOverviewListFromOther)

            }
        }



    }


    fun initListener() {
        binding.ivClose.setOnClickListener {
            binding.bodyYoutube.visibility = View.VISIBLE
            binding.bodyComment.visibility = View.GONE
        }

        binding.ivMoreComment.setOnClickListener {
            binding.bodyYoutube.visibility = View.GONE
            binding.bodyComment.visibility = View.VISIBLE
        }
    }
}





