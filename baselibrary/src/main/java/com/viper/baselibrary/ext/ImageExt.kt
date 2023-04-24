package com.viper.baselibrary.ext

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import coil.transform.*

/**
 * des 图片加载扩展方法
 * @author viper
 * @data 2020/6/26
 */


/**
 * 通过url加载
 */
fun ImageView.loadUrl(url: String) {
    load(url) {
        crossfade(true)
        memoryCachePolicy(CachePolicy.ENABLED) // 内存缓存
        diskCachePolicy(CachePolicy.ENABLED) // 磁盘缓存
        networkCachePolicy(CachePolicy.ENABLED) //网络缓存
    }
}

/**
 * 通过uri加载
 */
fun ImageView.loadUri(uri: Uri) {
    load(uri) {
        crossfade(true)
    }
}

/**
 * 高斯模糊加渐入渐出
 */
//fun ImageView.loadBlurTrans(context: Context, uri: Uri, radius: Int) {
//    load(uri) {
//        crossfade(true) //淡入淡出
//        placeholder(android.R.drawable.presence_away) //占位图
//        kotlin.error(android.R.drawable.stat_notify_error) //图片加载失败时显示的图
//        transformations(
//            BlurTransformation(context, 0.1f, 8f) //用于实现高斯模糊
//            //CircleCropTransformation。用于将图片转换为圆形
//            //GrayscaleTransformation。用户实现将图片转换为灰色
//            //RoundedCornersTransformation。用于为图片添加圆角
//        )
//    }
//}


/**
 * 圆形图片
 */
fun ImageView.loadCircle(uri: Uri) {
    load(uri) {
        crossfade(true) //淡入淡出
        placeholder(android.R.drawable.presence_away) //占位图
        error(android.R.drawable.stat_notify_error)//图片加载失败时显示的图
        transformations(
            CircleCropTransformation() //将图片显示为圆形
            //BlurTransformation。用于实现高斯模糊
            //CircleCropTransformation。用于将图片转换为圆形
            //GrayscaleTransformation。用户实现将图片转换为灰色
            //RoundedCornersTransformation。用于为图片添加圆角
        )
    }
}

/**
 * 圆形图片
 */
fun ImageView.loadRadius(url: String) {
    load(url) {
        crossfade(true) //淡入淡出
        placeholder(android.R.drawable.presence_away) //占位图
        error(android.R.drawable.stat_notify_error)//图片加载失败时显示的图
        transformations(
            RoundedCornersTransformation(10f, 10f, 10f, 10f)
        )
    }
}


