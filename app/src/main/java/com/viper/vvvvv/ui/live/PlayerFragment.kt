package com.viper.vvvvv.ui.live

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.jarvanmo.exoplayerview.media.SimpleMediaSource
import com.jarvanmo.exoplayerview.ui.ExoVideoView
import com.skydoves.bindables.BindingFragment
import com.viper.vvvvv.R
import com.viper.vvvvv.databinding.FragmentPlayerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class PlayerFragment : BindingFragment<FragmentPlayerBinding>(R.layout.fragment_player) {

    private val args: PlayerFragmentArgs by navArgs()

    @Inject
    internal lateinit var liveUrlViewModelFactory: LiveUrlViewModel.LiveUrlViewModelFactory

    private val viewModel: LiveUrlViewModel by viewModels {
        LiveUrlViewModel.provideFactory(liveUrlViewModelFactory, args.roomInfo)
    }


    private lateinit var videoView: ExoVideoView
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


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("fragment onViewCreated")
        videoView = binding.videoView
        videoView.isPortrait =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        videoView.setBackListener { view, isPortrait ->
            if (isPortrait) {
                activity?.finish()
            }
            false
        }

//        videoView.setOrientationListener { orientation ->
//            if (orientation == OnOrientationChangedListener.SENSOR_PORTRAIT) {
//            } else if (orientation == OnOrientationChangedListener.SENSOR_LANDSCAPE) {
//            }
//        }

        viewModel.viewModelScope.launch {
            viewModel.urlResponseData.collect { it ->
                it?.get("OD")?.let {
                    val mediaSource = SimpleMediaSource(it)
                    mediaSource.setDisplayName("Apple HLS")

                    videoView.play(mediaSource, true)
                }
            }
        }

    }

    private fun changeToPortrait() {
        val attributes = activity?.window?.attributes
        val window = activity?.window
        window?.attributes = attributes
        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }

    private fun changeToLandscape() {
        val attributes = activity?.window?.attributes
        val window = activity?.window
        window?.attributes = attributes
        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT <= 23) {
            videoView.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT <= 23) {
            videoView.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > 23) {
            videoView.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.releasePlayer()
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        return if (keyCode == KeyEvent.KEYCODE_BACK) {
//            videoView.onKeyDown(keyCode, event)
//        } else super.onKeyDown(keyCode, event)
//    }
}
