package com.danghuong.demoyoutubeapi.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.danghuong.demoyoutubeapi.ui.fragment.account.FragAccount
import com.danghuong.demoyoutubeapi.ui.fragment.home.FragHome
import com.danghuong.demoyoutubeapi.ui.fragment.search.FragSearch


class MainActivityAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    //   private var fragHome: FragHome?=null
//   private var fragTrending: FragTrending?=null
//   private var fragSearch: FragSearch?=null
//   private var fragAccount: FragAccount?=null
    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragHome()
            }
            1 -> {
                FragSearch()
            }
            else -> {
                FragAccount()
            }
        }
    }
}