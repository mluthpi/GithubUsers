package com.example.myprofiles.details.following

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myprofiles.User
import com.example.myprofiles.databinding.ListUserBinding

class FollowingAdapter(private val onItemClick: (following: User)-> Unit): RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    private val userItemList = mutableListOf<User>()

    fun addItems(userItemList: List<User>) {
        this.userItemList.clear()
        this.userItemList.addAll(userItemList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(userItem: User) {
            with(binding) {
                Glide.with(binding.root)
                    .load(userItem.avatarUrl)
                    .into(imgAvatar)
                tvName.text = userItem.login
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listUserBinding = ListUserBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(listUserBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userItem = userItemList[position]
        holder.bind(userItem)
        holder.itemView.setOnClickListener { onItemClick(userItem) }
    }

    override fun getItemCount(): Int = userItemList.size
}