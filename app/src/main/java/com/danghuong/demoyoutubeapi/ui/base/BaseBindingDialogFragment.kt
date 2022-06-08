package com.danghuong.demoyoutubeapi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.danghuong.demoyoutubeapi.MainViewModel

abstract class BaseBindingDialogFragment<B: ViewDataBinding, M : BaseViewModel> : BaseDialogFragment(){
    lateinit var binding: B
    lateinit var viewModel: M
    lateinit var mainViewModel : MainViewModel
    abstract val getLayoutId: Int
    abstract fun getViewModel(): Class<M>
    abstract fun onCreatedView(view: View, savedInstaceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(getViewModel())
        mainViewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        onCreatedView(view,savedInstanceState)
    }
}