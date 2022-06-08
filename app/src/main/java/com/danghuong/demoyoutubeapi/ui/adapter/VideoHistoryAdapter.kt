package com.danghuong.demoyoutubeapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.data.model.VideoOverview
import com.danghuong.demoyoutubeapi.data.model.history.History
import com.danghuong.demoyoutubeapi.databinding.ItemVideoHistoryBinding
import com.danghuong.demoyoutubeapi.databinding.ItemVideoSearchBinding

class VideoHistoryAdapter : RecyclerView.Adapter<VideoHistoryAdapter.HistoryHolder>() {
    private var listofother: MutableList<History> = mutableListOf()
    var videoItemClick: VideoItemClick? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setVideoHistoryList(listofother: MutableList<History>) {
        this.listofother.clear()
        this.listofother.addAll(listofother)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val binding =
            ItemVideoHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        Glide.with(holder.binding.ivHistory).load(R.drawable.ic_baseline_history_24)
            .into(holder.binding.ivHistory)
//        Glide.with(holder.binding.ivRemoveHistory).load(R.drawable.custom_remove_history)
//            .into(holder.binding.ivRemoveHistory)
        holder.binding.tvHistory.text = listofother[position].videoName
        holder.itemView.setOnClickListener(View.OnClickListener { videoItemClick?.onClick(position) })
    }

    override fun getItemCount(): Int {
        return if (listofother.size != null) listofother.size else 0

    }

    interface VideoItemClick {
        fun onClick(position: Int)
    }

    class HistoryHolder(var binding: ItemVideoHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}