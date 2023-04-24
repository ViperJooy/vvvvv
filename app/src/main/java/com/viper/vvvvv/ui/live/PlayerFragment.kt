package com.viper.vvvvv.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.skydoves.bindables.BindingFragment
import com.viper.player.databinding.ExoPlayerViewBinding
import com.viper.player.extension.*
import com.viper.player.util.CustomPlaybackState
import com.viper.vvvvv.R
import com.viper.vvvvv.databinding.FragmentPlayerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PlayerFragment : BindingFragment<FragmentPlayerBinding>(R.layout.fragment_player) {

    private val exoBinding: ExoPlayerViewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ExoPlayerViewBinding.bind(binding.root)
    }

    private val args: PlayerFragmentArgs by navArgs()

    @Inject
    internal lateinit var liveUrlViewModelFactory: LiveUrlViewModel.LiveUrlViewModelFactory

    private val viewModel: LiveUrlViewModel by viewModels {
        LiveUrlViewModel.provideFactory(liveUrlViewModelFactory, args.roomInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewModelScope.launch {
            viewModel.urlResponseData.collect { urlResponseData ->
                activity?.let { a ->
                    urlResponseData?.get("OD")?.let {
                        viewModel.onActivityCreate(it, a)
                    }
                }
            }
        }

        activity?.resolveSystemGestureConflict()
        initClickListeners()

        with(viewModel) {
            playerLiveData.observe(this@PlayerFragment) { exoPlayer ->
                binding.exoPlayerView.player = exoPlayer
            }

            playbackStateLiveData.observe(this@PlayerFragment) { playbackState ->
                setProgressbarVisibility(playbackState)
                setVideoControllerVisibility(playbackState)
            }

            isMuteLiveData.observe(
                this@PlayerFragment,
                exoBinding.exoControllerPlaceholder.muteButton::setMuteState
            )
        }
    }




    private fun initClickListeners() {
        with(exoBinding.exoControllerPlaceholder) {
            exoBackButton.setOnClickListener {
                activity?.onBackPressed()
            }
            playPauseButton.setOnClickListener { viewModel.onPlayButtonClicked() }
            muteButton.setOnClickListener { viewModel.onMuteClicked() }
            replayButton.setOnClickListener { viewModel.onReplayClicked() }
        }
    }

    private fun setProgressbarVisibility(playbackState: CustomPlaybackState) {
        binding.spinKit.isVisible = playbackState == CustomPlaybackState.LOADING
    }

    private fun setVideoControllerVisibility(playbackState: CustomPlaybackState) {
        exoBinding.exoControllerPlaceholder.run {
            playPauseButton.setState(playbackState)
            exoBackButton.visibility = View.GONE
            exoProgress.visibility = View.GONE
            exoPosition.visibility = View.GONE
            exoDuration.visibility = View.GONE

            when (playbackState) {
                CustomPlaybackState.PLAYING,
                CustomPlaybackState.PAUSED -> {
                    root.visible()
                    replayButton.gone()
                }
                CustomPlaybackState.ERROR,
                CustomPlaybackState.ENDED -> {
                    replayButton.visible()
                }
                else -> {
                    replayButton.gone()
                }
            }
        }
    }


    private fun releasePlayerView() {
        with(binding.exoPlayerView) {
            removeAllViews()
            player = null
        }
    }


    override fun onResume() {
        super.onResume()
        activity?.hideSystemUI(binding.root)
    }


    override fun onDestroyView() {
        super.onDestroyView()
//        releasePlayerView()
    }

    companion object {
        private const val DIALOG_TAG = "dialogTag"
    }

}
