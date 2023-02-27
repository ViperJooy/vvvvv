package com.viper.vvvvv.ui.live


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.viper.baselibrary.base.LazyBindingFragment
import com.viper.baselibrary.ext.initFragment
import com.viper.vvvvv.R
import com.viper.vvvvv.databinding.FragmentTabBinding
import com.viper.vvvvv.model.LiveTabBean

class TabFragment : LazyBindingFragment<FragmentTabBinding>(R.layout.fragment_tab) {
    private var tabList = FRAGMENTS

    override fun lazyInit() {
        binding.viewPager2.initFragment(this, arrayListOf<Fragment>().apply {
            tabList.forEach {
                add(LiveFragment.newInstance().apply {
                    //想各个fragment传递信息
                    val bundle = Bundle()
                    bundle.putString("platform", it.platform)
                    bundle.putString("tableName", it.tabName)
                    arguments = bundle
                })
            }
        })

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = FRAGMENTS[position].tabName
        }.attach()

    }


    companion object {
        private val FRAGMENTS = mutableListOf(
            LiveTabBean("all", "所有平台"),
            LiveTabBean("douyu", "斗鱼直播"),
            LiveTabBean("huya", "虎牙直播"),
            LiveTabBean("bilibili", "哔哩哔哩")
        )
    }

}