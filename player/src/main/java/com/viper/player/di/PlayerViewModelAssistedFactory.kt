package com.viper.player.di

import com.viper.player.argument.PlayerParams
import com.viper.player.viewmodel.PlayerViewModel


@dagger.assisted.AssistedFactory
interface PlayerViewModelAssistedFactory {
    fun create(playerParams: PlayerParams): PlayerViewModel
}