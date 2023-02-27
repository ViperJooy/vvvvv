package com.viper.baselibrary.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.skydoves.bindables.BindingFragment
import com.viper.baselibrary.utils.ParamUtil

abstract class LazyBindingFragment<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingFragment<T>(contentLayoutId) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ParamUtil.initParam(this)
    }


    private var isLoaded = false
    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

}