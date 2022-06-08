package com.danghuong.demoyoutubeapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.databinding.ItemHomeBinding
import com.danghuong.demoyoutubeapi.ui.utils.Convert
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var listofvideos: MutableList<VideoOverview> = mutableListOf()
    lateinit var videoItemClick: VideoItemClick

    @SuppressLint("NotifyDataSetChanged")
    fun setListofvideos(listofvideos: MutableList<VideoOverview>) {
        this.listofvideos.clear()
        this.listofvideos.addAll(listofvideos)
        notifyDataSetChanged()
    }
//
//    fun setVideoItemClick(videoItemClick: VideoItemClick){
//        this.videoItemClick=videoItemClick
//    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }

    @OptIn(ExperimentalTime::class)
    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        Glide.with(holder.binding.ivThumbnails)
            .load(listofvideos[position].videoItem.snippetVideoItem.thumbnailsVideo.high.url)
            .into(holder.binding.ivThumbnails)
        holder.binding.tvTitle.text =
            listofvideos[holder.absoluteAdapterPosition].videoItem.snippetVideoItem.title
        holder.binding.tvChannel.text =
            listofvideos[holder.absoluteAdapterPosition].videoItem.snippetVideoItem.channelTitle + " - " + Convert.convertViewCount(
                holder.binding.root.context,
                listofvideos[holder.absoluteAdapterPosition].videoItem.statisticVideoItem.viewCount
            )
        Glide.with(holder.binding.ivRepresenvativeImage)
            .load(listofvideos[position].videoItem.snippetVideoItem.thumbnailsVideo.high.url)
            .into(holder.binding.ivRepresenvativeImage)
        holder.binding.tvDuration.text =
            Convert.convertDuration(Duration.parse(listofvideos[position].videoItem.contentDetailVideo.duration).inWholeSeconds)
        holder.itemView.setOnClickListener(View.OnClickListener { videoItemClick.onClick(holder.absoluteAdapterPosition) })
    }

    override fun getItemCount(): Int {
        return listofvideos.size
    }

    interface VideoItemClick {
        fun onClick(position: Int)
    }

    class HomeHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)

}
