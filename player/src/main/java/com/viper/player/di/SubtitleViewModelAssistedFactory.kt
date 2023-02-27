package com.viper.player.di

import com.viper.player.argument.VideoSubtitle
import com.viper.player.viewmodel.SubtitleViewModel


@dagger.assisted.AssistedFactory
interface SubtitleViewModelAssistedFactory {
    fun create(videoSubtitles: List<VideoSubtitle>): SubtitleViewModel
}