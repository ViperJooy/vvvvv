
package com.viper.vvvvv.ui.live

import android.content.Context
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.google.android.exoplayer2.ExoPlayer
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import com.viper.vvvvv.model.RoomInfo
import com.viper.vvvvv.repository.MainRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class LiveUrlViewModel @AssistedInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val roomInfo: RoomInfo,
) : BindingViewModel() {

    //exoplayer
    private val _playerLiveData = MutableLiveData<ExoPlayer>()
    val playerLiveData: LiveData<ExoPlayer> = _playerLiveData

    private val _isMuteLiveData = MutableLiveData<Boolean>(false)
    val isMuteLiveData: LiveData<Boolean> = _isMuteLiveData

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val urlInfoFlow: Flow<Map<String, String>?> = mainRepository.getLiveRealUrl(
        roomInfo,
        onComplete = { isLoading = false },
        onError = { toastMessage = it })


    @get:Bindable
    val urlResponseData = urlInfoFlow


//    @get:Bindable
//    val urlInfo: Map<String, String>? by urlInfoFlow.asBindingProperty(viewModelScope, null)

    override fun onCleared() {
        super.onCleared()
        _playerLiveData.value?.let {
//            release()
        }
    }

    fun onActivityCreate(context: Context) {
        viewModelScope.launch {
            urlInfoFlow.collect {
                if (_playerLiveData.value == null) {
//                    setupPlayer(context, it)
                }
            }
        }
    }



//    private fun setupPlayer(context: Context, map: Map<String, String>?) {
//        with(playerRepository) {
//            _playerLiveData.value = createPlayer(context)
//            map?.get("OD")?.let {
//                val playerParams = PlayerParams(map["OD"].toString())
//                preparePlayer(createMediaItem(playerParams))
//                addEventListener(playerEventListener)
//                play()
//            }
//        }
//    }


    init {
        Timber.e("init PlayerViewModel")
    }

    @AssistedFactory
    interface LiveUrlViewModelFactory {
        fun create(roomInfo: RoomInfo): LiveUrlViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: LiveUrlViewModelFactory,
            roomInfo: RoomInfo,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(roomInfo) as T
            }
        }
    }
}
