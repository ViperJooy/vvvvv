package com.viper.vvvvv

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import android.webkit.CookieManager
import okhttp3.Cookie
import java.lang.StringBuilder

/**
 * author: viper
 * date: 2021-08-19 5:13 下午
 * desc: SharedPrefObject
 */
object SharedPrefObject {

    const val KEY_IS_LOGIN = "isLogin"
    const val KEY_LOGIN_NAME = "name"
    const val KEY_LOGIN_KEY = "key"
    const val KEY_LOGIN_AVATAR = "avatar"

    private fun getSharePref(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.instance)
    }

    private fun getCookieSharePref(): SharedPreferences {
        return MyApplication.instance.getSharedPreferences("cookies", Context.MODE_PRIVATE)
    }

    fun put(key: String, value: String) {
        getSharePref().edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return getSharePref().getString(key, "")
    }

    fun put(key: String, value: Boolean) {
        getSharePref().edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return getSharePref().getBoolean(key, false)
    }

    /**
     *
     * 只保存了一部分，可以查看cookies.xml中。但是传进来的Cookie还包含secure、httponly、expires
     * todo 本方法做了两件事吧？：保存到cookies.xml中、和cookieManager.setCookie()。暂时不清楚cookieManager.setCookie是为什么
     */
    fun saveCookies(cookies: List<Cookie>) {
        val editor = getCookieSharePref().edit()
        val cookie = StringBuilder()
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookies.forEach {
            editor.putString(it.name(), it.value())
            cookie.append(it.name()).append("=").append(it.value()).append(";")
            cookieManager.setCookie(it.domain(), "${it.name()}=${it.value()}")
        }
        editor.apply()
        cookieManager.flush()
    }

    /**
     * 生成的cookies比服务器传进来的cookies少了secure、httponly、expires
     */
    fun getCookies(): ArrayList<Cookie> {
        val sp = getCookieSharePref()
        return ArrayList<Cookie>().apply {
            val names = sp.all.keys
            names.forEach {
                add(
                    Cookie.Builder()
                        .domain(ApiService.BASEURL)
                        .name(it)
                        .value(sp.getString(it, "") ?: "")
                        .build()
                )
            }
        }
    }

    /**
     *
     * 因为在saveCookies时cookieManager.setCookie()了，所以退出登录时要cookieManager.removeSessionCookies()
     * 故：如果是用的Cookie 本地化方案二，则不需要调用下面的logout了
     * todo 当退出登录后，进入收藏界面不通过是否登录判断的话，还可以打开我的收藏页面。故说明cookies还在，感觉应该在退出登录后，清了cookies.xml。当然如果通过是否登录来判断是否能进入收藏界面的话，就不存在该bug了
     */
    fun logout() {
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.removeSessionCookies(null)
        cookieManager.removeAllCookies(null)
        cookieManager.flush()
    }
}