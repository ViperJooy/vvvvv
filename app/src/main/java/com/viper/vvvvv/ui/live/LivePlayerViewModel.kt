package com.viper.vvvvv.ui.live

import android.content.Context
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import com.viper.player.argument.PlayerParams
import com.viper.player.argument.VideoSubtitle
import com.viper.player.di.PlayerViewModelAssistedFactory
import com.viper.player.repository.PlayerRepository
import com.viper.player.util.CustomPlaybackState
import com.viper.player.util.createMediaItem
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
    private val playerRepository: PlayerRepository,
    @Assisted private val roomInfo: RoomInfo,
) : BindingViewModel() {

    private val _playerLiveData = MutableLiveData<ExoPlayer>()
    val playerLiveData: LiveData<ExoPlayer> = _playerLiveData

    private val _playbackStateLiveData = MutableLiveData(CustomPlaybackState.LOADING)
    val playbackStateLiveData: LiveData<CustomPlaybackState> = _playbackStateLiveData

    private val _isMuteLiveData = MutableLiveData<Boolean>(false)
    val isMuteLiveData: LiveData<Boolean> = _isMuteLiveData

    private var playerEventListener: Player.Listener? = getPlayerEventLister()

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    /**
     * 方式一 使用 MutableStateFlow，然后  viewModelScope.launch {urlRoomInfoStateFlow.emit()}
     *  private val urlRoomInfoStateFlow: MutableStateFlow<RoomInfo> = MutableStateFlow(roomInfo)
    private val urlStateFlow = urlRoomInfoStateFlow.flatMapLatest { roomInfo ->
    mainRepository.getLiveRealUrl(
    roomInfo,
    onComplete = { isLoading = false },
    onError = { toastMessage = it })
    }
     */

    /**
     * 方式二
     */
    private val urlInfoFlow: Flow<Map<String, String>?> = mainRepository.getLiveRealUrl(
        roomInfo,
        onComplete = { isLoading = false },
        onError = { toastMessage = it })

    //    @get:Bindable
//    val urlResponseData by urlInfoFlow.asBindingProperty(viewModelScope, null)
    val urlResponseData = urlInfoFlow


    init {
        Timber.e("init PlayerViewModel")

    }


    override fun onCleared() {
        super.onCleared()
        release()
    }

    fun onActivityCreate(url: String, context: Context) {
        viewModelScope.launch {
            if (_playerLiveData.value == null) {
                setupPlayer(url, context)
            }
        }
    }

    fun onPlayButtonClicked() {
        if (playbackStateLiveData.value != CustomPlaybackState.ERROR) {
            playerRepository.togglePlayingState()
        }
    }

    fun onReplayClicked() {
        when (playbackStateLiveData.value) {
            CustomPlaybackState.ERROR -> {
                playerRepository.rePrepareAndPlay()
            }
            CustomPlaybackState.ENDED -> {
                playerRepository.seekToDefaultPosition()
            }
            else -> return
        }
    }

    fun onMuteClicked() {
        with(playerRepository) {
            toggleMuteState()
            _isMuteLiveData.value = isMute()
        }
    }

    private fun getPlayerParam(url: String): PlayerParams {
        val subtitleList = mutableListOf<VideoSubtitle>()

        return PlayerParams(
            url = url,
            subtitles = subtitleList
        )
    }

    private fun setupPlayer(url: String, context: Context) {
        with(playerRepository) {
            _playerLiveData.value = createPlayer(context)
            //playerParams
            preparePlayer(createMediaItem(getPlayerParam(url)))
            addEventListener(playerEventListener)
            play()
        }
    }

    private fun release() {
        with(playerRepository) {
            removeEventListener(playerEventListener)
            playerEventListener = null
            release()
        }
    }

    internal fun getPlayerEventLister(): Player.Listener = object : Player.Listener {

        override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
            _playbackStateLiveData.value = when (reason) {
                Player.PLAY_WHEN_READY_CHANGE_REASON_USER_REQUEST,
                Player.PLAY_WHEN_READY_CHANGE_REASON_REMOTE -> {
                    if (playWhenReady) {
                        CustomPlaybackState.PLAYING
                    } else {
                        CustomPlaybackState.PAUSED
                    }
                }
                Player.PLAY_WHEN_READY_CHANGE_REASON_AUDIO_FOCUS_LOSS,
                Player.PLAY_WHEN_READY_CHANGE_REASON_AUDIO_BECOMING_NOISY -> CustomPlaybackState.PAUSED
                else -> return
            }
        }

        override fun onPlaybackStateChanged(state: Int) {
            _playbackStateLiveData.value = when (state) {
                Player.STATE_ENDED -> CustomPlaybackState.ENDED
                Player.STATE_READY -> {
                    if (playerRepository.isPlayerReady()) {
                        CustomPlaybackState.PLAYING
                    } else {
                        CustomPlaybackState.PAUSED
                    }
                }
                Player.STATE_BUFFERING -> CustomPlaybackState.LOADING
                else -> return
            }
        }

        override fun onEvents(player: Player, events: Player.Events) {
            super.onEvents(player, events)
            if (events.contains(Player.EVENT_IS_LOADING_CHANGED)) {
                val isLoading = player.isLoading
                val isNotPlaying = player.isPlaying.not()
                if (isLoading && isNotPlaying) {
                    _playbackStateLiveData.value = CustomPlaybackState.LOADING
                }
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            _playbackStateLiveData.value = CustomPlaybackState.ERROR
        }
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
