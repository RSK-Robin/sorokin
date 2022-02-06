package com.sorokin.gifviewer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class GifPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val sectionTypes = GifsType.values()

    override fun getItemCount(): Int = sectionTypes.size

    override fun createFragment(position: Int): Fragment =
        SectionGifFragment.newInstance(sectionTypes[position])
}
