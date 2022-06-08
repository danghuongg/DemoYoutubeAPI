package com.danghuong.demoyoutubeapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.data.model.comment.CommentItem
import com.danghuong.demoyoutubeapi.data.model.videos.VideoItem
import com.danghuong.demoyoutubeapi.databinding.ItemCommentBinding
import com.danghuong.demoyoutubeapi.databinding.ItemHomeBinding
import com.danghuong.demoyoutubeapi.ui.utils.Convert
import timber.log.Timber
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    private var listofcomment: MutableList<VideoItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setListofComment(listofcomment: MutableList<VideoItem>) {
        this.listofcomment.clear()
        this.listofcomment.addAll(listofcomment)
        notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentHolder(binding)
    }


    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
          Timber.e("huongdt: ui")
        Glide.with(holder.binding.ivChannelImageComment)
            .load(listofcomment[holder.absoluteAdapterPosition].snippetVideoItem.topLevelComment.snippet.authorProfileImageUrL)
            .placeholder(R.drawable.images)
            .into(holder.binding.ivChannelImageComment)
        holder.binding.tvCmt.text = listofcomment[holder.absoluteAdapterPosition].snippetVideoItem.topLevelComment.snippet.textOriginal
    }

    override fun getItemCount(): Int {
       return   listofcomment.size
    }


    class CommentHolder(var binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)
}