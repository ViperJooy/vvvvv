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

package com.viper.vvvvv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.viper.vvvvv.databinding.ItemRoomBinding
import com.viper.vvvvv.model.RoomInfo

//import com.viper.vvvvv.ui.player.PlayerLiveActivity

class RoomAdapter(
    private val onItemClickListener: ((room: RoomInfo) -> Unit)
) :
    PagingDataAdapter<RoomInfo, RoomAdapter.RoomViewHolder>(diffUtil) {
    private var onClickedAt = 0L


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bindRoom(item)
        }
    }

    inner class RoomViewHolder constructor(
        private val binding: ItemRoomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
                    ?: return@setOnClickListener
                val item = getItem(position) as RoomInfo
                onItemClickListener.invoke(item)

//                val currentClickedAt = SystemClock.elapsedRealtime()
//                if (currentClickedAt - onClickedAt > binding.transformationLayout.navigation-safe-args-gradle-pluginduration) {
//                    getItem(position)?.let { it1 ->

//                        PlayerLiveActivity.startActivity(
//                            binding.transformationLayout,
//                            it1
//                        )
//                    }
//                    onClickedAt = currentClickedAt
//                }
            }
        }

        fun bindRoom(room: RoomInfo) {
            binding.room = room
            binding.online = getWan(room.online)
            binding.executePendingBindings()
        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RoomInfo>() {

            override fun areItemsTheSame(oldItem: RoomInfo, newItem: RoomInfo): Boolean =
                oldItem.roomId + oldItem.roomName == newItem.roomId + newItem.roomName

            override fun areContentsTheSame(oldItem: RoomInfo, newItem: RoomInfo): Boolean =
                oldItem == newItem
        }

        private fun getWan(num: Int): String {
            val numString = num.toString().trim()
            return if (numString.length > 4) {
                val numCut = numString.substring(0, numString.length - 4)
//                val afterPoint = numString.substring(numString.length - 4, numString.length - 3)
                numCut + '万'
//                numCut + '.' + afterPoint + '万'
            } else {
                numString + '人'
            }
        }
    }
}
