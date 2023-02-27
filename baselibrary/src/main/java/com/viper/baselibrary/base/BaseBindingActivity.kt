package com.viper.baselibrary.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.skydoves.bindables.BindingActivity

abstract class BaseBindingActivity<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingActivity<T>(contentLayoutId) {

}