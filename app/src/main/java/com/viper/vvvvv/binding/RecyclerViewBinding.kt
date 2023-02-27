package com.viper.vvvvv.binding


import androidx.databinding.BindingAdapter
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.whatif.whatIfNotNullAs
import com.viper.vvvvv.model.RoomInfo
import com.viper.vvvvv.ui.adapter.RoomAdapter
import com.viper.vvvvv.ui.live.LiveViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter", "submitList")
    fun bindAdapter(view: RecyclerView, adapter: RoomAdapter, viewModel: LiveViewModel) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        viewModel.viewModelScope.launch {
            viewModel.roomList.collectLatest { pagingData ->
                view.adapter.whatIfNotNullAs<PagingDataAdapter<RoomInfo, *>> { adapter ->
                    adapter.submitData(pagingData!!)
                }
            }
        }
    }
}
