/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.viper.vvvvv.binding

import android.graphics.Color
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.skydoves.whatif.whatIfNotNullOrEmpty
import com.viper.baselibrary.ext.loadRadius
import com.viper.baselibrary.ext.loadUrl
import com.viper.baselibrary.ext.toast
import com.viper.vvvvv.extensions.gone
import com.viper.vvvvv.extensions.visible
import me.ibrahimsn.lib.SmoothBottomBar
import kotlin.math.abs

object ViewBinding {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImage(view: AppCompatImageView, url: String?) {
        url?.let {
            view.loadUrl(url)
        }
    }


    /**
     * 加载网络圆角图片
     */
    @BindingAdapter(value = ["loadImageRadius"])
    @JvmStatic
    fun bindLoadImageRadiusCircle(view: ImageView, url: String) {
        url?.let {
            view.loadRadius(url)
        }
    }



    @JvmStatic
    @BindingAdapter("pagerAdapter")
    fun bindPagerAdapter(view: ViewPager2, adapter: FragmentStateAdapter?) {
        view.adapter = adapter
        view.offscreenPageLimit = view.childCount

        //取消viewPager2滑动
//        view?.isUserInputEnabled = true
    }

    @JvmStatic
    @BindingAdapter("bindNavigation")
    fun bindNavigation(view: ViewPager2?, smoothBottomBar: SmoothBottomBar?) {

        view?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                smoothBottomBar?.itemActiveIndex = position
            }

            override fun onPageScrollStateChanged(state: Int) = Unit
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) = Unit
        })

        smoothBottomBar?.onItemSelected = { view?.currentItem = it }

    }

//    @JvmStatic
//    @BindingAdapter("gone")
//    fun bindGone(view: View, shouldBeGone: Boolean?) {
//        if (shouldBeGone == true) {
//            view.gone(true)
//        } else {
//            view.gone(false)
//        }
//    }

    @JvmStatic
    @BindingAdapter("bindFab")
    fun bindAppBarLayoutWithFab(appBarLayout: AppBarLayout, fab: FloatingActionButton) {
        appBarLayout.addOnOffsetChangedListener(
            OnOffsetChangedListener { appBarLayout1: AppBarLayout, verticalOffset: Int ->
                val verticalOffsetPercentage = abs(
                    verticalOffset
                ).toFloat() / appBarLayout1.totalScrollRange.toFloat()
                if (verticalOffsetPercentage > 0.4f && fab.isOrWillBeShown) {
                    fab.hide()
                } else if (verticalOffsetPercentage <= 0.4f && fab.isOrWillBeHidden && fab.tag != View.GONE) {
                    fab.show()
                }
            }
        )
    }

    @JvmStatic
    @BindingAdapter("transformFab", "transformContainer")
    fun bindTransformFab(view: View, fab: FloatingActionButton, container: CoordinatorLayout) {
        fab.setOnClickListener {
            // Begin the transition by changing properties on the start and end views or
            // removing/adding them from the hierarchy.
            fab.tag = View.GONE
            TransitionManager.beginDelayedTransition(container, getTransform(fab, view))
            fab.gone(true)
            view.visible()
        }

        view.setOnClickListener {
            fab.tag = View.VISIBLE
            TransitionManager.beginDelayedTransition(container, getTransform(view, fab))
            fab.visible()
            view.gone(true)
        }
    }

    private fun getTransform(mStartView: View, mEndView: View): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            startView = mStartView
            endView = mEndView
            addTarget(mEndView)
            pathMotion = MaterialArcMotion()
            duration = 550
            scrimColor = Color.TRANSPARENT
        }
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text.whatIfNotNullOrEmpty {
            Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }


}
