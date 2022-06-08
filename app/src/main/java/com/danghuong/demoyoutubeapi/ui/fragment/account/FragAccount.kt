package com.danghuong.demoyoutubeapi.ui.fragment.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.common.Common
import com.danghuong.demoyoutubeapi.databinding.FragAccountBinding
import com.danghuong.demoyoutubeapi.ui.base.BaseActivity
import com.danghuong.demoyoutubeapi.ui.base.BaseBindingFragment
import com.danghuong.demoyoutubeapi.ui.utils.LocaleUtils
import java.util.*

class FragAccount : BaseBindingFragment<FragAccountBinding, AccountVM>() {
    override val getLayoutId: Int
        get() = R.layout.frag_account

    override fun getViewModel(): Class<AccountVM> {
        return AccountVM::class.java
    }

    override fun onPermissionGranted() {
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreatedView(view: View, savedInstaceState: Bundle?) {

        binding.tvVnLanguage.setOnClickListener {
           LocaleUtils.applyLocaleAndRestart(requireActivity(),Common.LANGUAGE_VN)
        }
        binding.ivVnLanguageFlag.setOnClickListener {
           LocaleUtils.applyLocaleAndRestart(requireActivity(),Common.LANGUAGE_VN)
        }
        binding.tvEnLanguage.setOnClickListener {
            LocaleUtils.applyLocaleAndRestart(requireActivity(),Common.LANGUAGE_EN)
        }
        binding.ivEnLanguageFlag.setOnClickListener {
            LocaleUtils.applyLocaleAndRestart(requireActivity(),Common.LANGUAGE_EN)
        }


    }
}