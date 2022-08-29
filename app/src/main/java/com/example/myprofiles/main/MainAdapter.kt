package com.example.myprofiles.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myprofiles.ResponseItem
import com.example.myprofiles.databinding.ListUserBinding

class MainAdapter(val onItemlick: (user: ResponseItem)-> Unit): RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    private val userItemList = mutableListOf<ResponseItem>()

    fun setList(userItemList: List<ResponseItem>) {
        this.userItemList.clear()
        this.userItemList.addAll(userItemList)
        notifyDataSetChanged()
    }

    class ViewHolder (private val binding: ListUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(userItem: ResponseItem) {
            with(binding) {
                Glide.with(binding.root)
                    .load(userItem.avatarUrl)
                    .into(imgAvatar)
                tvName.text = userItem.login

                Log.d("TAG", "bind: avatarUrl: ${userItem.avatarUrl}")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listUserBinding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(listUserBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userItem = userItemList[position]
        holder.bind(userItem)
        holder.itemView.setOnClickListener{onItemlick(userItem)}

    }

    override fun getItemCount(): Int = userItemList.size


}