package com.viper.vvvvv.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.skydoves.bindables.BindingFragment
import com.viper.baselibrary.ext.toast
import com.viper.vvvvv.MainActivity
import com.viper.vvvvv.R
import com.viper.vvvvv.databinding.FragmentMainBinding
import com.viper.vvvvv.ui.adapter.MainPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding {
            (activity as MainActivity)?.setSupportActionBar(toolBar)
            setupDrawerLayout()
            pagerAdapter = activity?.let { MainPagerAdapter(it) }
        }.root
    }

    private fun setupDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            activity, binding.drawerLayout, binding.toolBar, 0, 0
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // items.add("onDrawerSlide($slideOffset)")
                // adapter.notifyDataSetChanged()
            }

            override fun onDrawerOpened(drawerView: View) {
//                items.add("onDrawerOpened()")
//                adapter.notifyDataSetChanged()
            }

            override fun onDrawerClosed(drawerView: View) {
//                items.add("onDrawerClosed()")
//                adapter.notifyDataSetChanged()
            }

            override fun onDrawerStateChanged(newState: Int) {
//                items.add("onDrawerStateChanged($newState)")
//                adapter.notifyDataSetChanged()
            }

        })

        //侧边栏点击事件
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_camera -> {
                    toast("nav_camera")
                }

                R.id.nav_gallery -> {
                    toast("nav_gallery")
                }

                R.id.nav_slideshow -> {
                    toast("nav_slideshow")
                }

                R.id.nav_manage -> {
                    toast("nav_manage")
                }

                R.id.nav_share -> {
                    toast("nav_share")
                }

                R.id.nav_send -> {
                    toast("nav_send")
                }
            }
            true
        }
    }

}