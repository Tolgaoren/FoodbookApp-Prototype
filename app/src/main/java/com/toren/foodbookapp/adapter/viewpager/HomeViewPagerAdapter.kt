package com.toren.foodbookapp.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.toren.foodbookapp.ui.view.HomeViewPagerFragment
import com.toren.foodbookapp.utils.Constants

class HomeViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return Constants.yemekKategorileri.size
    }

    override fun createFragment(position: Int): Fragment {
        return HomeViewPagerFragment(Constants.yemekKategorileri[position])
    }

}