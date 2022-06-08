package com.danghuong.demoyoutubeapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.search.SearchItem
import com.danghuong.demoyoutubeapi.databinding.ItemOthersVideoBinding
import com.danghuong.demoyoutubeapi.ui.utils.Convert
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class OtherVideosAdapter : RecyclerView.Adapter<OtherVideosAdapter.OtherVideosAdapter>() {
    private var listofother: MutableList<VideoOverview> = mutableListOf()
     var videoItemClick: VideoItemClick?=null

    @SuppressLint("NotifyDataSetChanged")
    fun setOthersList(listofother: MutableList<VideoOverview>) {
        this.listofother.clear()
        this.listofother.addAll(listofother)
        notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherVideosAdapter {
        val binding =
            ItemOthersVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherVideosAdapter(binding)
    }


    @OptIn(ExperimentalTime::class)
    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: OtherVideosAdapter, position: Int) {
        Glide.with(holder.binding.ivThumbnails)
            .load(listofother[position].videoItem.snippetVideoItem.thumbnailsVideo.high.url)
            .into(holder.binding.ivThumbnails)
        holder.binding.tvTitle.text =
            listofother[holder.absoluteAdapterPosition].videoItem.snippetVideoItem.title
        holder.binding.tvChannel.text =
            listofother[holder.absoluteAdapterPosition].videoItem.snippetVideoItem.channelTitle + " - " + Convert.convertViewCount(holder.binding.root.context, listofother[holder.absoluteAdapterPosition].videoItem.statisticVideoItem.viewCount)
        Glide.with(holder.binding.ivRepresenvativeImage)
            .load(listofother[position].videoItem.snippetVideoItem.thumbnailsVideo.high.url)
            .into(holder.binding.ivRepresenvativeImage)
        holder.binding.tvDuration.text =
            Convert.convertDuration(Duration.parse(listofother[position].videoItem.contentDetailVideo.duration).inWholeSeconds)
        holder.itemView.setOnClickListener(View.OnClickListener { videoItemClick?.onClick(position) })
    }

    override fun getItemCount(): Int {
            return listofother.size

    }

interface VideoItemClick {
    fun onClick(position: Int)
}

class OtherVideosAdapter(var binding: ItemOthersVideoBinding) :
    RecyclerView.ViewHolder(binding.root)
}