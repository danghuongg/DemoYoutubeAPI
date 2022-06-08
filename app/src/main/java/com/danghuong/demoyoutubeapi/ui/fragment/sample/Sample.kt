package com.danghuong.demoyoutubeapi.ui.fragment.sample

import android.os.Bundle
import android.view.View
import com.danghuong.demoyoutubeapi.R
import com.danghuong.demoyoutubeapi.databinding.DialogSampleBinding
import com.danghuong.demoyoutubeapi.ui.base.BaseBottomSheetDialogFragment

class Sample: BaseBottomSheetDialogFragment<DialogSampleBinding, SampleVM>() {
    override fun getViewModel(): Class<SampleVM> {
       return SampleVM::class.java
    }

    override fun onCreatedView(view: View, savedInstanceState: Bundle?) {
    }

    override val getLayoutId: Int
        get() = R.layout.dialog_sample
}