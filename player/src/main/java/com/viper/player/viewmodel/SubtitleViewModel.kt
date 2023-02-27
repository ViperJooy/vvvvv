package com.viper.player.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.viper.player.repository.PlayerRepository
import com.viper.player.util.track.MediaTrack
import com.viper.player.util.track.TrackEntity
import com.viper.player.R
import com.viper.player.argument.VideoSubtitle
import com.viper.player.datasource.TrackSelectorDataSource
import com.viper.player.di.SubtitleViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class SubtitleViewModel @AssistedInject constructor(
    @Assisted private val videoSubtitles: List<VideoSubtitle>,
    private val trackSelectorDataSource: TrackSelectorDataSource,
    private val playerRepository: PlayerRepository
) : ViewModel() {

    private val _subtitleEntitiesLiveData = MutableLiveData<List<TrackEntity>>()
    val subtitleEntitiesLiveData: LiveData<List<TrackEntity>> = _subtitleEntitiesLiveData

    private val _onSubtitleSelectedLiveData = MutableLiveData<Unit>()
    val onSubtitleSelectedLiveData: LiveData<Unit> = _onSubtitleSelectedLiveData

    private val subtitleOffText = R.string.player_subtitle_off

    private val subtitles = mutableListOf<MediaTrack>()
    private var selectedSubtitle: TrackEntity? = null

    var playerEventListener: Player.Listener? = getPlayerEventLister()
        private set

    override fun onCleared() {
        super.onCleared()
        resetState()
    }

    fun onActivityCrated() {
        if (subtitles.isNotEmpty()) {
            return
        }
        playerRepository.addEventListener(playerEventListener)
    }

    private fun onSubtitleSelected(index: Int) {
        if (isInValidToSelectSubtitle(index)) {
            return
        }
        _onSubtitleSelectedLiveData.value = Unit
        with(trackSelectorDataSource) {
            val selectedSubtitleCafeTrack = updateSelectedTrack(
                index,
                subtitles,
                subtitles[0].rendererIndex
            )
            updateTrackEntities(
                subtitles,
                selectedSubtitleCafeTrack,
                subtitleOffText,
                ::onSubtitleSelected
            )?.let { tracks ->
                _subtitleEntitiesLiveData.value = tracks
                selectedSubtitle = subtitleEntitiesLiveData.value?.get(index)
            }
        }
    }

    private fun getPlayerEventLister(): Player.Listener = object : Player.Listener {
        override fun onPlaybackStateChanged(state: Int) {
            if (state == Player.STATE_READY && subtitles.isEmpty()) {
                setupSubtitleConfig()
            }
        }
    }

    private fun setupSubtitleConfig() {
        with(trackSelectorDataSource) {
            subtitles.apply {
                clear()
                addAll(
                    trackMapper.map(C.TRACK_TYPE_TEXT, videoSubtitles.map { it.title })
                )
            }
            updateTrackEntities(
                subtitles,
                null,
                subtitleOffText,
                ::onSubtitleSelected
            )?.let { tracks -> _subtitleEntitiesLiveData.value = tracks }

            onSubtitleSelected(DEFAULT_SUBTITLE_INDEX)
        }
    }

    private fun isInValidToSelectSubtitle(index: Int): Boolean {
        return subtitles.isEmpty() || isValidIndex(index).not()
    }

    private fun isValidIndex(index: Int): Boolean {
        return index >= 0 && index <= subtitles.size
    }

    private fun resetState() {
        subtitles.clear()
        selectedSubtitle = null
        playerRepository.removeEventListener(playerEventListener)
        playerEventListener = null
    }

    companion object {
        private const val DEFAULT_SUBTITLE_INDEX = 0

        fun provideFactory(
            assistedFactory: SubtitleViewModelAssistedFactory,
            videoSubtitles: List<VideoSubtitle>
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(videoSubtitles) as T
            }
        }
    }
}