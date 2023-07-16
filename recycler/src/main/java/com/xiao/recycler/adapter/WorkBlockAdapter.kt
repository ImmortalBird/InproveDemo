package com.xiao.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.xiao.recycler.databinding.WorkItemAppIconBinding

class WorkBlockAdapter(
    private val data: MutableList<WorkBlockItem>,
    val onItemClick: (WorkBlockItem, Int) -> Unit
) :

    Adapter<WorkBlockAdapter.WorkBlockHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkBlockHolder {
        val context = parent.context
//        val resources = context.resources
////        val marginHorizontal = resources.getDimensionPixelSize(R.dimen.dimen_29)
////        val itemWidth = resources.getDimensionPixelSize(R.dimen.dimen_40)
//        val sWidth = resources.displayMetrics.widthPixels
////        val rest = sWidth// - (marginHorizontal.shl(1)) - itemWidth
//        val per = sWidth / (itemCount)
////        val d = per - itemWidth
//
        val binding =
            WorkItemAppIconBinding.inflate(LayoutInflater.from(context), parent, false)
//        binding.root.layoutParams =
//            RecyclerView.LayoutParams(
//                per,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )

        return WorkBlockHolder(binding)

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WorkBlockHolder, position: Int) {
        val item = data[position]
        holder.binding.tv.text = item.name
    }


    override fun onBindViewHolder(
        holder: WorkBlockHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        val item = data[position]

    }





    class WorkBlockHolder(val binding: WorkItemAppIconBinding) : ViewHolder(binding.root)

    data class WorkBlockItem(val name: String, val img: String ="", val routerId:String= "")


}