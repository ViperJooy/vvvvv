package com.viper.baselibrary.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.skydoves.bindables.BindingFragment
import com.viper.baselibrary.utils.ParamUtil


abstract class BaseBindingFragment<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingFragment<T>(contentLayoutId) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        ParamUtil.initParam(this)
    }



}