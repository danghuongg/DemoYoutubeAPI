package com.danghuong.demoyoutubeapi.ui.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


abstract class BaseBindingActivity<B : ViewDataBinding, M : BaseViewModel> : BaseActivity() {
    lateinit var binding: B
    lateinit var viewModel: M
    abstract val layoutId: Int
    abstract fun getViewModel(): Class<M>
    abstract fun setupView(savedInstanceState: Bundle?)
    abstract fun setupData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel= ViewModelProvider(this).get(getViewModel())
        setupView(savedInstanceState)
        setupData()


    }

}