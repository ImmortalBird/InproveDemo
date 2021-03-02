package com.example.slidmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.requestDisallowInterceptTouchEvent(true)
        rv.adapter = MyAdapter()
    }


    class MyAdapter : Adapter<MyHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.tv.text = "$position"
        }

    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView by lazy { itemView.findViewById<TextView>(R.id.tv) }
    }
}