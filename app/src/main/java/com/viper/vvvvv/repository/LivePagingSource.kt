package com.viper.vvvvv.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.viper.vvvvv.model.RoomInfo
import com.viper.vvvvv.network.ApiService
import timber.log.Timber
import java.lang.Exception

class LivePagingSource(
    private val apiService: ApiService,
    private val platform: String
) : PagingSource<Int, RoomInfo>() {

    override fun getRefreshKey(state: PagingState<Int, RoomInfo>): Int? = null

//    override fun getRefreshKey(state: PagingState<Int, RoomInfo>): Int? {
//        // We need to get the previous key (or next key if previous is null) of the page
//        // that was closest to the most recently accessed index.
//        // Anchor position is the most recently accessed index
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoomInfo> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
//            val repository = apiService.getRecommendLiveRoomList(page, pageSize)
            val repository = when (platform) {
                "all" -> apiService.getRecommendLiveRoomList(page, pageSize)
                else -> apiService.getRecommendByPfLiveRoomList(platform, page, pageSize)
            }
//            val repository = apiService.getRecommendByPfLiveRoomList("bilibili",page, pageSize)
//            val repository = apiService.getRecommendByPfAreaLiveRoomList("bilibili","英雄联盟",page, pageSize)
//            val repository = apiService.getRecommendByAreaAllLiveRoomList("网游竞技","炉石传说",page, pageSize)
            val items = repository.data
            Timber.d("$items")
            val preKey = if (page > 1) page - 1 else null
            val nextKey = if (items.isNotEmpty()) page + 1 else null
            LoadResult.Page(items, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}