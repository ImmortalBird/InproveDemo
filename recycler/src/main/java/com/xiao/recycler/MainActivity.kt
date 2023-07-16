package com.xiao.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiao.recycler.adapter.WorkMainAdapter
import com.xiao.recycler.databinding.ActivityMainBinding
import com.xiao.recycler.decoration.LinearDecoration

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: MutableList<WorkMainAdapter.WorkMenuItem> = mutableListOf()
        data.add(WorkMainAdapter.WorkMenuItem("首页应用"))
        data.add(WorkMainAdapter.WorkMenuItem("我的应用"))
        data.add(WorkMainAdapter.WorkMenuItem("经营管理"))
        data.add(WorkMainAdapter.WorkMenuItem("协同办公"))
        data.add(WorkMainAdapter.WorkMenuItem("业务应用"))
        val space = resources.getDimensionPixelOffset(R.dimen.dimen_12)
        binding.rv.addItemDecoration(LinearDecoration(
            topSpace = space,
            bottomSpace = space.shl(1),
            startSpace = space,
            endSpace = space,
            spaceMiddle = space
        ))
        binding.rv.adapter = WorkMainAdapter(data){item,pos->

        }
    }
}
