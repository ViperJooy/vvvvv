package com.viper.vvvvv.ui.video

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.viper.vvvvv.DanMuActivity
import com.viper.vvvvv.R

class VideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        startDanMuActivity(getString(R.string.media_url_66))
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    private fun startDanMuActivity(url: String) {
        val args = Bundle().apply {
            putString("url", url)
        }
        Intent(context, DanMuActivity::class.java).run {
            putExtras(args)
            startActivity(this)
        }
    }

}
