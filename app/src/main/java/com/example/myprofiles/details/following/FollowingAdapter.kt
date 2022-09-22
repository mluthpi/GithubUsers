package com.example.myprofiles.details.following

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myprofiles.User
import com.example.myprofiles.databinding.ListUserBinding
import com.example.myprofiles.model.UserDetailsResponse

class FollowingAdapter: RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    private val userItemList = arrayListOf<UserDetailsResponse>()

    fun addItems(userItemList: List<UserDetailsResponse>) {
        this.userItemList.clear()
        this.userItemList.addAll(userItemList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(userItem: UserDetailsResponse) {
            with(binding) {
                Glide.with(binding.root)
                    .load(userItem.followingUrl)
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

    }

    override fun getItemCount(): Int = userItemList.size
}