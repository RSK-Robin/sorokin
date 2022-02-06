package com.sorokin.gifviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.sorokin.gifviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        if(savedInstanceState == null){
            binding.viewPager.currentItem = 0
        }

        setSupportActionBar(binding.includeToolbar.toolbar)

        binding.viewPager.apply {
            adapter = GifPagerAdapter(supportFragmentManager, lifecycle)
        }

        TabLayoutMediator(binding.includeToolbar.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.gif_tab_array)[position]
        }.attach()
    }
}