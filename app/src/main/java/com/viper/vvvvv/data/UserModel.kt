package com.viper.vvvvv.data

/**
 * author: viper
 * date: 2021-08-19 4:32 下午
 * desc: User
 * {
"error": true,
"info": "登录成功",
"key": "b1a17d02f8930438c15a8d12f693f766",
"nickname": "刘畅",
"avatar": "http://geely-dop-prod.oss-cn-hangzhou.aliyuncs.com/eg/8dbe5e44-e0f8-4f4c-96eb-0d2faa20ff6a.png?Expires=17285241600&OSSAccessKeyId=LTAIpXuucgQxsVrq&Signature=1RZWl3Q8K5zBEYMlfx3q%2BlqKgwc%3D",
"viptime": "0",
"join_co": "0",
"time_str": "0"
}
 */

data class UserModel(
    val error: Boolean,
    val info: String,
    val key: String,
    val nickname: String,
    val avatar: String,
    val viptime: String,
    val join_co: String,
    val time_str: String,
)
