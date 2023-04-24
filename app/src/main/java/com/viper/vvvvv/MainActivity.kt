package com.viper.vvvvv

import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import com.gyf.immersionbar.ktx.immersionBar
import com.viper.baselibrary.base.BaseBindingActivity
import com.viper.vvvvv.databinding.ActivityMainBinding
import com.viper.vvvvv.extensions.applyExitMaterialTransform
import com.viper.vvvvv.ui.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>(R.layout.activity_main) {
//    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        applyExitMaterialTransform()
        super.onCreate(savedInstanceState)


//        immersionBar {
//            statusBarView(binding.mainView)
//            autoStatusBarDarkModeEnable(true, 0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//        }

        //设置 获取statusBar高度
//        binding.mainViewpager.setPaddingRelative(0, StatusUtils.getStatusBarHeightNew(this), 0, 0)


//        initToolBar()
    }

    private fun initToolBar() {
//        navController = findNavController(R.id.main_viewpager)
//        navController = Navigation.findNavController()

        //设置toolbar
//        setSupportActionBar(binding.toolBar)
//        setupActionBarWithNavController(findNavController(R.id.liveFragment))

//        设置bottom
//        setupSmoothBottomMenu()

        //设置drawerLayout
//        setupDrawerLayout()

    }


//    private fun setupSmoothBottomMenu() {
//        val popupMenu = PopupMenu(this, null)
//        popupMenu.inflate(R.menu.menu_bottom)
//        val menu = popupMenu.menu
//        binding.bottomBar.setupWithNavController(menu, navController)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        //获取hostFragment
        val mMainNavFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        //获取当前所在的fragment
        val fragment = mMainNavFragment?.childFragmentManager?.primaryNavigationFragment

        //如果当前处于根fragment即HostFragment
        //如果是mainFragment Activity退出但不销毁
        if (fragment is MainFragment) moveTaskToBack(false) else super.onBackPressed()
    }
}