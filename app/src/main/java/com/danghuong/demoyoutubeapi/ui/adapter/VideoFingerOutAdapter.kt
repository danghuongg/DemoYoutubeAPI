package com.danghuong.demoyoutubeapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.databinding.ItemVideoFigureOutBinding
import com.danghuong.demoyoutubeapi.databinding.ItemVideoSearchBinding
import com.danghuong.demoyoutubeapi.ui.utils.Convert
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class VideoFingerOutAdapter: RecyclerView.Adapter<VideoFingerOutAdapter.VideoFingerOutHolder>() {
    private var listofother: MutableList<VideoOverview> = mutableListOf()
    var videoItemClick: VideoItemClick? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setVideoFingerOutList(listofother: MutableList<VideoOverview>) {
        this.listofother.clear()
        this.listofother.addAll(listofother)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoFingerOutHolder {
        val binding =
            ItemVideoFigureOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoFingerOutHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    @OptIn(ExperimentalTime::class)
    override fun onBindViewHolder(holder: VideoFingerOutHolder, position: Int) {
        Glide.with(holder.binding.ivThumbnails)
            .load(listofother[position].videoItem.snippetVideoItem.thumbnailsVideo.high.url)
            .into(holder.binding.ivThumbnails)
        holder.binding.tvTitle.text =
            listofother[holder.absoluteAdapterPosition].videoItem.snippetVideoItem.title
        holder.binding.tvChannel.text =
            listofother[holder.absoluteAdapterPosition].videoItem.snippetVideoItem.channelTitle + " - " + Convert.convertViewCount(
                holder.binding.root.context,
                listofother[holder.absoluteAdapterPosition].videoItem.statisticVideoItem.viewCount
            )
        Glide.with(holder.binding.ivRepresenvativeImage)
            .load(listofother[position].videoItem.snippetVideoItem.thumbnailsVideo.high.url)
            .into(holder.binding.ivRepresenvativeImage)
        holder.binding.tvDuration.text =
            Convert.convertDuration(Duration.parse(listofother[position].videoItem.contentDetailVideo.duration).inWholeSeconds)
        holder.itemView.setOnClickListener(View.OnClickListener { videoItemClick?.onClick(holder.absoluteAdapterPosition) })
    }

    override fun getItemCount(): Int {
        return if (listofother.size != null) listofother.size else 0

    }

    interface VideoItemClick {
        fun onClick(position: Int)
    }

    class VideoFingerOutHolder(var binding: ItemVideoFigureOutBinding) :
        RecyclerView.ViewHolder(binding.root)
}