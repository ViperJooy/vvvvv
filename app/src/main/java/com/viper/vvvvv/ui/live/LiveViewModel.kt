package com.viper.vvvvv.ui.live

import androidx.databinding.Bindable
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import com.viper.vvvvv.model.LiveTabBean
import com.viper.vvvvv.model.RoomInfo
import com.viper.vvvvv.repository.MainRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class LiveViewModel @AssistedInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val tabBean: LiveTabBean
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val roomFetchingIndex = MutableStateFlow(flowOf<PagingData<RoomInfo>>())

    private val roomListFlow: Flow<PagingData<RoomInfo>> = roomFetchingIndex.flatMapLatest {
        mainRepository.fetchLiveRoomList(tabBean.platform).cachedIn(viewModelScope)
    }


    @get:Bindable
    var roomList: Flow<PagingData<RoomInfo>?> = roomListFlow
        private set

    init {
        Timber.d("init LiveViewModel")
    }


    @AssistedFactory
    interface LiveViewModelFactory {
        fun create(tabBean: LiveTabBean): LiveViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: LiveViewModelFactory,
            tabBean: LiveTabBean
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(tabBean) as T
            }
        }
    }
}