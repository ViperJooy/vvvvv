package com.viper.vvvvv

import android.app.Application
import com.viper.vvvvv.utils.MMKVUtil

/**
 * author: viper
 * date: 2021-08-19 5:14 下午
 * desc: MyApplication
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        MMKVUtil.init(this)
    }

    companion object {
        lateinit var instance: Application
    }
}