package com.xiaobing.improvedemo.custom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xiaobing.improvedemo.custom.fragment.FlowLayoutFragment
import com.xiaobing.improvedemo.custom.viewModel.FragmentBean

class ViewPagerAdapter(fa : FragmentActivity,val data: List<FragmentBean>) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {


        return FlowLayoutFragment()
    }
}