package com.xiao.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.xiao.recycler.databinding.WorkItemNormalBlockBinding
import com.xiao.recycler.adapter.WorkBlockAdapter.WorkBlockItem
import com.xiao.recycler.decoration.GiftDecoration

class WorkMainAdapter(
    private val data: MutableList<WorkMenuItem>,
    val onItemClick: (WorkMenuItem, Int) -> Unit
) :

    Adapter<WorkMainAdapter.WorkMainHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkMainHolder {
        val context = parent.context
        val resources = context.resources
//        val marginHorizontal = resources.getDimensionPixelSize(R.dimen.dimen_29)
//        val itemWidth = resources.getDimensionPixelSize(R.dimen.dimen_40)
        val sWidth = resources.displayMetrics.widthPixels
//        val rest = sWidth// - (marginHorizontal.shl(1)) - itemWidth
        val per = sWidth / (itemCount)
//        val d = per - itemWidth

        val binding =
            WorkItemNormalBlockBinding.inflate(LayoutInflater.from(context), parent, false)
//        binding.root.layoutParams =
//            RecyclerView.LayoutParams(
//                per,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )

        return WorkMainHolder(binding)

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WorkMainHolder, position: Int) {
        val data: MutableList<WorkBlockItem> = mutableListOf()
        data.add(WorkBlockItem("首页应用"))
        data.add(WorkBlockItem("我的应用"))
        data.add(WorkBlockItem("经营管理"))
        data.add(WorkBlockItem("协同办公"))
        data.add(WorkBlockItem("业务应用"))
        data.add(WorkBlockItem("首页应用1"))
        data.add(WorkBlockItem("我的应用1"))
        data.add(WorkBlockItem("经营管理1"))
        data.add(WorkBlockItem("协同办公1"))
        data.add(WorkBlockItem("业务应用1"))
        data.add(WorkBlockItem("首页应用2"))
        data.add(WorkBlockItem("我的应用2"))
        data.add(WorkBlockItem("经营管理2"))
        data.add(WorkBlockItem("协同办公2"))
        data.add(WorkBlockItem("业务应用2"))
        data.add(WorkBlockItem("首页应用3"))
        data.add(WorkBlockItem("我的应用3"))
        data.add(WorkBlockItem("经营管理3"))
        data.add(WorkBlockItem("协同办公3"))
        data.add(WorkBlockItem("业务应用3"))
        data.add(WorkBlockItem("首页应用4"))
        data.add(WorkBlockItem("我的应用4"))
        data.add(WorkBlockItem("经营管理4"))
        data.add(WorkBlockItem("协同办公4"))
        data.add(WorkBlockItem("业务应用4"))
        data.add(WorkBlockItem("首页应用5"))
        data.add(WorkBlockItem("我的应用5"))
        data.add(WorkBlockItem("经营管理5"))
        data.add(WorkBlockItem("协同办公5"))
        data.add(WorkBlockItem("业务应用5"))
        holder.binding.rv.apply {
            layoutManager = GridLayoutManager(holder.binding.root.context, 4)

            addItemDecoration(GiftDecoration(10))
            adapter = WorkBlockAdapter(data) { item, pos ->

            }
        }


    }


    override fun onBindViewHolder(
        holder: WorkMainHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        val item = data[position]
    }


    class WorkMainHolder(val binding: WorkItemNormalBlockBinding) : ViewHolder(binding.root)

    data class WorkMenuItem(val name: String)


}