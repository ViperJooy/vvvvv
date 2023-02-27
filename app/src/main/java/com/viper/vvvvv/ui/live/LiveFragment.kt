package com.viper.vvvvv.ui.live

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.skydoves.bindables.BindingFragment
import com.viper.baselibrary.ext.toast
import com.viper.vvvvv.R
import com.viper.vvvvv.databinding.FragmentLiveBinding
import com.viper.vvvvv.model.LiveTabBean
import com.viper.vvvvv.ui.MainFragmentDirections
import com.viper.vvvvv.ui.adapter.RoomAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

private const val ARG_PLATFORM = "platform"
private const val ARG_TAB_NAME = "tabName"

@AndroidEntryPoint
class LiveFragment : BindingFragment<FragmentLiveBinding>(R.layout.fragment_live) {

    private var platform: String? = null
    private var tabName: String? = null

    @Inject
    internal lateinit var liveViewModelFactory: LiveViewModel.LiveViewModelFactory

    private val vm: LiveViewModel by viewModels {
        arguments?.let {
            platform = it.getString(ARG_PLATFORM).toString()
            tabName = it.getString(ARG_TAB_NAME).toString()
        }
        LiveViewModel.provideFactory(liveViewModelFactory, LiveTabBean(platform!!, tabName!!))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            viewModel = vm
            adapter = RoomAdapter { room ->
                val action = MainFragmentDirections.actionHomeFragmentToPlayFragment(room)
                findNavController().navigate(action)
            }
        }.root
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(activity, 4)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("fragment onViewCreated")

        binding.adapter?.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
//                    binding.progressbar?.visibility = View.INVISIBLE
                    binding.recyclerView?.visibility = View.VISIBLE
                    binding.swipeRefreshLayout?.isRefreshing = false
                }
                is LoadState.Loading -> {
//                    binding.progressbar?.visibility = View.VISIBLE
//                    binding.recyclerView?.visibility = View.INVISIBLE
                    binding.swipeRefreshLayout?.isRefreshing = true
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
//                    binding.progressbar?.visibility = View.INVISIBLE
                    binding.swipeRefreshLayout?.isRefreshing = false
                    toast("Load Error: ${state.error.message}")
                }
            }
        }

        binding.swipeRefreshLayout?.setOnRefreshListener {
            binding.recyclerView?.swapAdapter(binding.adapter, false)
            binding.adapter?.refresh()

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(platform: String, tabName: String) =
            LiveFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLATFORM, platform)
                    putString(ARG_TAB_NAME, tabName)
                }
            }

        fun newInstance() = LiveFragment()
    }
}
