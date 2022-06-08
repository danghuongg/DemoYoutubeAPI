package com.danghuong.demoyoutubeapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.search.VideoSearchInSeachFragment
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.databinding.ItemOthersVideoBinding
import com.danghuong.demoyoutubeapi.databinding.ItemVideoSearchBinding
import com.danghuong.demoyoutubeapi.ui.utils.Convert
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class VideoSearchAdapter : RecyclerView.Adapter<VideoSearchAdapter.VideoSearchHolder>() {
    private var listofother: MutableList<VideoOverview> = mutableListOf()
    var videoItemClick: VideoItemClick? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setVideoSearchList(listofother: MutableList<VideoOverview>) {
        this.listofother.clear()
        this.listofother.addAll(listofother)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoSearchHolder {
        val binding =
            ItemVideoSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoSearchHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoSearchHolder, position: Int) {
        Glide.with(holder.binding.ivSeach).load(R.drawable.ic_baseline_search_24)
            .into(holder.binding.ivSeach)
        holder.binding.tvQuery.text = listofother[position].videoItem.snippetVideoItem.title
        holder.itemView.setOnClickListener(View.OnClickListener { videoItemClick?.onClick(position) })
    }

    override fun getItemCount(): Int {
        return if (listofother.size != null) listofother.size else 0

    }

    interface VideoItemClick {
        fun onClick(position: Int)
    }

    class VideoSearchHolder(var binding: ItemVideoSearchBinding) :
        RecyclerView.ViewHolder(binding.root)
}
