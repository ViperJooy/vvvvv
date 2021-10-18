package com.viper.vvvvv.data

/**
 * author: viper
 * date: 2021-08-19 4:39 下午
 * desc: ApiResponse
 */
data class ApiResponse<T>(
    val error: Boolean,
    val info: T?,
    val msg: String
)
