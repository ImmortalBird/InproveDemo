package com.xiaobing.improvedemo.custom

import com.google.android.material.tabs.TabLayoutMediator
import com.xiaobing.improvedemo.base.activity.BaseMvvmActivity
import com.xiaobing.improvedemo.base.activity.BaseViewBindingActivity
import com.xiaobing.improvedemo.custom.viewModel.CustomViewViewModel
import com.xiaobing.improvedemo.custom.viewModel.FragmentBean
import com.xiaobing.improvedemo.databinding.ActivityCustomViewMainBinding

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/26
 *
 * 自定义控件的总入口 activity
 */
class CustomViewMainActivity : BaseMvvmActivity<CustomViewViewModel,ActivityCustomViewMainBinding>() {
    override fun initView() {
        mViewModel?.list?.add(FragmentBean(0,"流式布局"))
        mViewModel?.list?.add(FragmentBean(0,"流式布局"))
        mViewModel?.list?.add(FragmentBean(0,"流式布局"))
        mViewModel?.list?.add(FragmentBean(0,"流式布局"))
        mBinding.apply {
            val data = mViewModel?.list ?:  return
            viewPager.adapter = ViewPagerAdapter(this@CustomViewMainActivity , data)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = data[position].desc
            }.attach()
        }
    }


}