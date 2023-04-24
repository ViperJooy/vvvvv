/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.viper.vvvvv.repository

import androidx.annotation.WorkerThread
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.skydoves.whatif.whatIfNotNullOrEmpty
import com.viper.vvvvv.model.RoomInfo
import com.viper.vvvvv.network.ApiService
import com.viper.vvvvv.persistence.RoomDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val roomDao: RoomDao,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    init {
        Timber.d("Injection MainRepository")
    }

    companion object {
        private const val PAGE_SIZE = 10
    }

//    @WorkerThread
//    fun fetchLiveRoomList() = Pager(
//        config = PagingConfig(PAGE_SIZE),
//        pagingSourceFactory = { LivePagingSource(apiService) }
//    ).flow

    @WorkerThread
    fun fetchLiveRoomList(platform: String) = flow {
        Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { LivePagingSource(apiService, platform) }
        )
            .flow
            .collect {
                emit(it)
            }
    }.flowOn(ioDispatcher)


    @WorkerThread
    fun fetchLiveRoomList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = apiService.getRecommendLiveRoomList(page = page, PAGE_SIZE)
        if (response.code == 200) {
            var rooms = response.data
            emit(rooms)
        } else {
            onError(response.message)
        }
//        var rooms = roomDao.getRoomList(page)
//        if (rooms.isEmpty()) {
//            /**
//             * fetches a list of [Pokemon] from the network and getting [ApiResponse] asynchronously.
//             * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
//             */
//            val response = apiService.getRecommendLiveRoomList(page = page)
//            response.suspendOnSuccess {
//                rooms = data.data
//                rooms.forEach { room -> room.page = page }
//                roomDao.insertRoomList(rooms)
//                emit(roomDao.getAllRoomList(page))
//            }.onFailure { // handles the all error cases from the API request fails.
//                onError(message())
//            }
//        } else {
//            emit(roomDao.getAllRoomList(page))
//        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)


    @WorkerThread
    fun getLiveRealUrl(
        roomInfo: RoomInfo,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        /**
         * fetches a [PokemonInfo] from the network and getting [ApiResponse] asynchronously.
         * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
         */
        val response = apiService.getLiveRealUrl(roomInfo.platForm, roomInfo.roomId)
        Timber.e("$response")
        if (response.code == 200) {
            response.data.whatIfNotNullOrEmpty(
                whatIf = {
                    if (!response.data.containsKey("OD")) onError("OD视频直播流错误")
                    it.forEach {
                        Timber.e("${it.key}: ${it.value}")
                    }
                    emit(response.data)
                },
                whatIfNot = {
                    onError("未获取到直播地址")
                }
            )
        } else {
            onError(response.message)
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}

