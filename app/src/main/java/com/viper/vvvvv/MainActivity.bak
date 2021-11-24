package com.viper.vvvvv

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.viper.vvvvv.data.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var service: ApiService

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTheme(R.style.Theme_Vvvvv)//恢复默认主题样式
        setContentView(R.layout.activity_main)
        service = service()
        login("15605505160", "liu19961226c")
    }

    private fun login(user: String, pass: String) {
        val isLogin =
            MMKVUtil.get(MMKV_TYPE.USER).decodeBoolean(SharedPrefObject.KEY_IS_LOGIN)

        isLogin?.let {
            if (isLogin) {
                val loginName =
                    MMKVUtil.get(MMKV_TYPE.USER).decodeString(SharedPrefObject.KEY_LOGIN_NAME)
                val loginKey = MMKVUtil.get(MMKV_TYPE.USER).decodeString(SharedPrefObject.KEY_LOGIN_KEY)
                val loginAvatar =
                    MMKVUtil.get(MMKV_TYPE.USER).decodeString(SharedPrefObject.KEY_LOGIN_AVATAR)
                Snackbar.make(tv_mileage, loginName!!, Snackbar.LENGTH_LONG).show()
                tv_mileage.text = loginName
                return
            }

        }

        val call = service.login(user, pass)
        call?.enqueue(object : Callback<UserModel?> {
            override fun onResponse(call: Call<UserModel?>?, response: Response<UserModel?>) {
                try {
                    val body = response.body()
                    Log.d(TAG, response.body().toString())
                    body?.let {
                        if (it.error) {
                            MMKVUtil.get(MMKV_TYPE.USER).encode(SharedPrefObject.KEY_IS_LOGIN, true)
                            MMKVUtil.get(MMKV_TYPE.USER)
                                .encode(SharedPrefObject.KEY_LOGIN_NAME, it.nickname)
                            MMKVUtil.get(MMKV_TYPE.USER)
                                .encode(SharedPrefObject.KEY_LOGIN_KEY, it.key)
                            MMKVUtil.get(MMKV_TYPE.USER)
                                .encode(SharedPrefObject.KEY_LOGIN_AVATAR, it.avatar)
                        } else {
//                            Snackbar.make(, it.info, Snackbar.LENGTH_LONG).show()
                        }
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<UserModel?>?, t: Throwable) {
                t.printStackTrace()
//                Snackbar.make(, "网络错误🙅‍♂️", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun service() = Retrofit.Builder()
        .baseUrl(ApiService.BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}