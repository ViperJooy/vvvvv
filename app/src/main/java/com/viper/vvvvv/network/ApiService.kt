package com.viper.vvvvv.network

import com.viper.vvvvv.model.LiveRoomResponse
import com.viper.vvvvv.model.RoomInfo
import com.viper.vvvvv.model.UrlsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * author: viper
 * date: 2021-08-19 4:27 下午
 * desc: ApiService
 */
interface ApiService {

    /**
     * 根据分页信息获取所有支持直播平台的推荐直播间（根据观看人数降序排序）
     */
    @GET("/api/live/getRecommend")
    suspend fun getRecommendLiveRoomList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): LiveRoomResponse

    /**
     * 根据分页信息获取指定直播平台的推荐直播间（根据观看人数降序排序）
     */
    @GET("/api/live/getRecommendByPlatform")
    suspend fun getRecommendByPfLiveRoomList(
        @Query("platform") platform: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): LiveRoomResponse

    /**
     * 获取指定直播平台下特定分区（area）的推荐直播间信息
     */
    @GET("/api/live/getRecommendByPlatformArea")
    suspend fun getRecommendByPfAreaLiveRoomList(
        @Query("platform") platform: String,
        @Query("area") area: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): LiveRoomResponse


    /**
     * getRecommendByAreaAll
     */
    @GET("/api/live/getRecommendByAreaAll")
    suspend fun getRecommendByAreaAllLiveRoomList(
        @Query("areaType") areaType: String,
        @Query("area") area: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): LiveRoomResponse


    /**
     * 获取指定平台直播间的真实直播推流地址
     */
    @GET("api/live/getRealUrl")
    suspend fun getLiveRealUrl(
        @Query("platform") name: String,
        @Query("roomId") roomId: String
    ): UrlsResponse

//    @FormUrlEncoded
//    @POST("binduser")
//    fun login(@Field("user") user: String?, @Field("pass") pass: String?): Call<UserModel?>?
//
//    companion object {
//        const val BASEURL = "https://lynkco.hyphp.vip/Api2/"
//    }
}