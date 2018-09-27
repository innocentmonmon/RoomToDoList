package com.example.newuser.roomtodolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.card_view.view.*

class AppAdapter(val tasks: MutableList<RoomTask>) : RecyclerView.Adapter<AppAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.test.text = tasks[position].newTask
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var test = itemView.txt_card
    }
}