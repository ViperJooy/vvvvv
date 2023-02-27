package com.viper.vvvvv.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.viper.vvvvv.ui.live.TabFragment
import com.viper.vvvvv.ui.other.OtherFragment
import com.viper.vvvvv.ui.video.VideoFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragmentsCreators: Map<Int, () -> Fragment> = mapOf(
//        LIVE to { LiveFragment() },
        LIVE to { TabFragment() },
        VIDEO to { VideoFragment() },
        OTHER to { OtherFragment() }
    )

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    override fun getItemCount() = fragmentsCreators.size

    companion object {
        private const val LIVE = 0
        private const val VIDEO = 1
        private const val OTHER = 2
    }
}