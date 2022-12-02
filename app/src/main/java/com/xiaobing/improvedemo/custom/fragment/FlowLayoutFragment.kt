package com.xiaobing.improvedemo.custom.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xiaobing.improvedemo.R
import com.xiaobing.improvedemo.base.fragment.BaseMvvmFragment
import com.xiaobing.improvedemo.databinding.FragmentFlowLayoutBinding
import com.xiaobing.improvedemo.viewModel.DefaultViewModel

class FlowLayoutFragment : BaseMvvmFragment<FragmentFlowLayoutBinding, DefaultViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.fl.let {
            it.addView(getTextView("武昌鱼"))
            it.addView(getTextView("乳胶垫床垫"))
            it.addView(getTextView("单人床"))
            it.addView(getTextView("华为平板"))
            it.addView(getTextView("华为matebook x pro"))
            it.addView(getTextView("乳胶垫"))
            it.addView(getTextView("白象方便面"))
            it.addView(getTextView("a4打印纸"))
            it.addView(getTextView("京东小魔方新品"))
            it.addView(getTextView("买菜抢1分钱券包"))
            it.addView(getTextView("AR"))
        }
    }

    private fun getTextView(content: String): TextView {
        return TextView(context).apply {
            text = content
            setTextColor(Color.BLACK)
            textSize = 20f
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(30, 5, 30, 5)
            setBackgroundResource(R.drawable.shape_8dp)
        }
    }

}