package com.viper.baselibrary.ext

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * des 自定义DataBinding适配器
 * @author viper
 * @date 2020/6/28
 */
object CustomBindAdapter {

    /**
     * 加载资源图片
     */
    @BindingAdapter(value = ["imgSrc"])
    @JvmStatic
    fun imgSrc(view: ImageView, id: Int) {
        view.setImageResource(id)
    }

    /**
     * 加载图片
     */
    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String) {
        view.loadUrl(url)
    }


    /**
     * 加载网络圆角图片
     */
    @BindingAdapter(value = ["imgUrlRadius"])
    @JvmStatic
    fun imgUrlRadiusCircle(view: ImageView, url: String) {
        view.loadRadius(url)
    }


    @BindingAdapter(value = ["visible"])
    @JvmStatic
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

}