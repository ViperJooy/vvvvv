package com.viper.vvvvv

import com.viper.vvvvv.data.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: viper
 * date: 2021-08-19 4:27 下午
 * desc: ApiService
 */
interface ApiService {
    @FormUrlEncoded
    @POST("binduser")
    fun login(@Field("user") user: String?, @Field("pass") pass: String?): Call<UserModel?>?

    companion object {
        const val BASEURL = "https://lynkco.hyphp.vip/Api2/"
    }
}