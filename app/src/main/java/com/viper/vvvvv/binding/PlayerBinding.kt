package com.viper.vvvvv.binding

import android.content.Context
import android.net.Uri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.viper.vvvvv.R
import kotlinx.coroutines.launch


object PlayerBinding {
//    @JvmStatic
//    @BindingAdapter("load_video")
//    fun loadVideo(playerView: PlayerControlView, viewModel: LiveUrlViewModel) {
//        viewModel.viewModelScope.launch {
//            viewModel.urlResponseData.collect { map ->
//                map?.let {
//                    var playerUrl: String? = ""
//                    when {
//                        //原画
//                        !map["OD"].isNullOrEmpty() -> {
//                            playerUrl = map["OD"]
//                            Timber.e("OD")
//                        }
//
//                        //超清
//                        !map["HD"].isNullOrEmpty() -> {
//                            playerUrl = map["HD"]
//                            Timber.e("HD")
//                        }
//
//                        //高清
//                        !map["SD"].isNullOrEmpty() -> {
//                            playerUrl = map["SD"]
//                            Timber.e("SD")
//                        }
//
//                        //清晰
//                        !map["LD"].isNullOrEmpty() -> {
//                            playerUrl = map["LD"]
//                            Timber.e("LD")
//                        }
//
//                        //流畅
//                        !map["FD"].isNullOrEmpty() -> {
//                            playerUrl = map["FD"]
//                            Timber.e("FD")
//                        }
//                    }
//
//
//                    //自适应轨道
//                    var trackSelector = DefaultTrackSelector(playerView.context).apply {
//                        setParameters(buildUponParameters().setMaxVideoSizeSd())
//                    }
//
//                    val exoPlayer = ExoPlayer.Builder(playerView.context)
//                        .setLivePlaybackSpeedControl(
//                            DefaultLivePlaybackSpeedControl.Builder()
//                                .setFallbackMaxPlaybackSpeed(1.04f)
//                                .build()
//                        )
//                        .setTrackSelector(trackSelector)
//                        .build()
//
//                    playerView.player = exoPlayer
//
//                    exoPlayer.run {
//                        setMediaSource(
//                            buildMediaSource(
//                                playerView.context,
//                                Uri.parse(playerUrl)
//                            )
//                        )
//                        //seekTo(playbackPosition)
//                        playWhenReady = true
//                        prepare()
//                    }
//                }
//
//                playerView.player!!.addListener(object : Player.Listener {
//                    override fun onPlayerError(error: PlaybackException) {
//                        super.onPlayerError(error)
//                        if (error.errorCode == PlaybackException.ERROR_CODE_BEHIND_LIVE_WINDOW) {
//                            // Re-initialize player at the current live window default position.
//                            playerView.player!!.seekToDefaultPosition()
//                            playerView.player!!.prepare()
//                        } else {
//
//                            // Handle other errors.
//                            toast("Oops! Error occurred while playing media. $error")
//                        }
//
//                    }
//
//                    override fun onPlaybackStateChanged(playbackState: Int) {
//                        val stateString: String = when (playbackState) {
//                            /**
//                             * 播放器已实例化，但尚未准备就绪。
//                             */
//                            ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
//
//                            //播放器无法从当前位置开始播放，因为已缓冲的数据不足。
//                            ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
//
//                            /**
//                             * 播放器可以立即从当前位置开始播放。
//                             * 这意味着如果播放器的 playWhenReady 属性为 true，播放器将自动开始播放媒体。
//                             * 如果该属性为 false，播放器会暂停播放。
//                             */
//                            ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
//
//
//                            /**
//                             * 播放器已完成媒体播放。
//                             */
//                            ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
//                            else -> "UNKNOWN_STATE             -"
//                        }
//                        Log.d("MediaPlayerIMPL", "changed state to $stateString")
//                    }
//                })
//            }
//        }
//    }


//    private fun buildMediaSource(context: Context, uri: Uri): MediaSource {
//        val appName = context.getString(R.string.app_name)
//
//        val dataSourceFactory = DefaultDataSource.Factory(
//            context,
//            DefaultHttpDataSource.Factory()
//                .apply {
//                    setConnectTimeoutMs(DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS)
//                    setReadTimeoutMs(DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS)
//                    setAllowCrossProtocolRedirects(true)
////                        .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Mobile Safari/537.36")
////                    setUserAgent("Dart/2.14 (dart:io)")
//                    setUserAgent(Util.getUserAgent(context, appName))
//                })
//
//        return when (val type = Util.inferContentType(uri)) {
//            C.TYPE_DASH -> DashMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(MediaItem.fromUri(uri))
//            C.TYPE_SS -> SsMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(MediaItem.fromUri(uri))
//            C.TYPE_HLS -> HlsMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(MediaItem.fromUri(uri))
//            C.TYPE_OTHER -> ProgressiveMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(MediaItem.fromUri(uri))
//            else -> throw IllegalStateException("Unsupported type: $type")
//        }
//    }
}