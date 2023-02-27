package com.viper.baselibrary.ext

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.viper.baselibrary.AppHelper

/**
 * des Toast工具类
 * @date 2020/5/14
 * @author viper
 */

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    Toast.makeText(this, content, duration).apply {
        show()
    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}


fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    AppHelper.mContext.toast(content, duration)
}

fun toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    AppHelper.mContext.toast(id, duration)
}

fun longToast(content: String, duration: Int = Toast.LENGTH_LONG) {
    if (TextUtils.isEmpty(content)) return
    AppHelper.mContext.toast(content, duration)
}

fun longToast(@StringRes id: Int, duration: Int = Toast.LENGTH_LONG) {
    AppHelper.mContext.toast(id, duration)
}


