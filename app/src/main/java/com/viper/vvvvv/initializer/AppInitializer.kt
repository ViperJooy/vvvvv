package com.viper.vvvvv.initializer

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.startup.Initializer
import com.viper.baselibrary.AppHelper
import com.viper.baselibrary.utils.MMKVUtil
import com.viper.vvvvv.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(
//                ThreadPolicy.Builder().detectAll().penaltyLog().build()
//            )
//            StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())

            Timber.plant(DebugTree())
            Timber.d("TimberInitializer is initialized.")

        }
        AppHelper.init(context)
        MMKVUtil.init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}