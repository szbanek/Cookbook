package com.example.recipes2

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TopFragment()
            1 -> Tab1Fragment()
            2 -> Tab2Fragment()
            else -> throw IllegalArgumentException("Invalid page position: $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Start"
            1 -> "Kategoria 1"
            2 -> "Kategoria 2"
            else -> throw IllegalArgumentException("Invalid page position: $position")
        }
    }
}