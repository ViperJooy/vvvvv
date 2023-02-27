package com.viper.vvvvv.model


import android.graphics.Color
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class LiveRoomResponse(val code: Int, val message: String, val data: List<RoomInfo>)
data class UrlsResponse(val code: Int, val message: String, val data: MutableMap<String, String>)
data class RoomInfoResponse(val code: String, val message: String, val data: RoomInfo)
data class SearchResponse(val code: String, val message: String, val data: List<Owner>)
data class AreaAllResponse(val code: String, val message: String, val data: List<List<AreaInfo>>)
data class UserInfoResponse(val code: String, val message: String, val data: UserInfo)
data class FollowResponse(val code: String, val message: String, val data: String)
//data class UpdateResponse(val code: String, val message: String, val data: UpdateInfo)

@Entity
@Parcelize
data class AreaInfo(
    val platform: String,
    val areaType: String,
    val typeName: String,
    val areaId: String,
    val areaName: String,
    val areaPic: String,
    val shortName: String
) : Parcelable

@Entity
@Parcelize
data class RoomInfo(
    var page: Int = 1,
    @PrimaryKey val roomId: String,
    val platForm: String,
    val roomPic: String,
    val ownerHeadPic: String,
    val ownerName: String,
    val roomName: String,
    val categoryId: String,
    val categoryName: String,
    val online: Int,
    val isLive: Int,
    val isFollowed: Int,
//    val eGameToken: String
) : Parcelable

@Entity
@Parcelize
data class Owner(
    val platform: String,
    val nickName: String,
    val roomId: String,
    var headPic: String,
    val cateName: String,
    val isLive: String,
    val followers: Int,
    val isFollowed: Int
) : Parcelable

data class UserInfo(
    val uid: String,
    val userName: String,
    val nickName: String,
    val password: String,
    val head: String,
    var isActived: String,
    var allContent: String,
    var selectedContent: String,
    val douyuLevel: String,
    val bilibiliLevel: String,
    val huyaLevel: String,
    val ccLevel: String,
    val egameLevel: String
)

data class DanmuSetting(
    var isShow: Boolean,
    var showArea: Float,
    var alpha: Float,
    var speed: Float,
    var size: Float,
    var border: Float,
    var merge: Boolean,
    var bold: Boolean,
    var fps: Boolean
)

data class SendDanmuBean(
    var position: Long,
    var text: String = "",
    var isSmallSize: Boolean = false,
    var isScroll: Boolean = true,
    var isTop: Boolean = false,
    var color: Int = Color.WHITE,
)