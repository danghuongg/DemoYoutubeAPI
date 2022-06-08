package com.danghuong.demoyoutubeapi.ui.fragment.fragmain

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.View
import androidx.annotation.NonNull
import androidx.viewpager2.widget.ViewPager2
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.common.Event
import com.danghuong.demoyoutubeapi.databinding.FragmainBinding
import com.danghuong.demoyoutubeapi.receiver.NetworkReceiver
import com.danghuong.demoyoutubeapi.ui.adapter.MainActivityAdapter
import com.danghuong.demoyoutubeapi.ui.base.BaseActivity
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.annotations.NotNull
import timber.log.Timber
import java.util.*

class FragMain : BaseBindingFragment<FragmainBinding, FragMainVM>() {
    var status_networkChange: String? = null
    var mainActivityAdapet: MainActivityAdapter? = null



    override val getLayoutId: Int
        get() = R.layout.fragmain

    override fun getViewModel(): Class<FragMainVM> {
        return FragMainVM::class.java
    }

    override fun onPermissionGranted() {
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreatedView(view: View, savedInstaceState: Bundle?) {



        mainActivityAdapet = MainActivityAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = mainActivityAdapet
        binding.bottomNavHome.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.icon_home -> binding.viewPager.currentItem = 0
                R.id.icon_Search -> binding.viewPager.currentItem = 1
                R.id.icon_myAccount -> binding.viewPager.currentItem = 2
            }
            true
        }
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavHome.menu.findItem(R.id.icon_home).isChecked = true
                    1 -> binding.bottomNavHome.menu.findItem(R.id.icon_Search).isChecked = true
                    else -> binding.bottomNavHome.menu.findItem(R.id.icon_myAccount).isChecked = true
                }
            }
        })

    }

    override  fun onAttach( context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(event: Event) {
        if (event.typeEvent == Common.Status_Network) {
           if(event.isPlay == true){


               val handler = Handler(requireActivity().mainLooper)
               handler.postDelayed({
                   if (isAdded) {     //
                       binding.tvConnect.visibility = View.GONE
                   }
               }, 2000)

               binding.tvConnect.visibility = View.VISIBLE
               binding.ivDisconnect.visibility = View.GONE
           }
            else{
               binding.tvConnect.visibility = View.GONE
               binding.ivDisconnect.visibility = View.VISIBLE
           }
        }
    }


}
