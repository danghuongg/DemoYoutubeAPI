package com.danghuong.demoyoutubeapi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.danghuong.demoyoutubeapi.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding, T : BaseViewModel> : BottomSheetDialogFragment() {

    lateinit var binding: B
    lateinit var viewModel: T
    lateinit var mainViewModel : MainViewModel
    protected abstract fun getViewModel(): Class<T>
    abstract val getLayoutId: Int
    protected abstract fun onCreatedView(view: View, savedInstanceState: Bundle?)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        viewModel = ViewModelProvider(this).get(getViewModel())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(getViewModel())
        mainViewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        onCreatedView(view, savedInstanceState)
    }
}