package com.example.myprofiles.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myprofiles.databinding.ListRepoBinding
import com.example.myprofiles.model.UserRepositoryResponseItem

class DetailReposAdapter: RecyclerView.Adapter<DetailReposAdapter.ListRepoHolder>() {

    private val userReposList = mutableListOf<UserRepositoryResponseItem>()

    fun addRepos(userReposList: List<UserRepositoryResponseItem>) {
        this.userReposList.clear()
        this.userReposList.addAll(userReposList)
        notifyDataSetChanged()
    }


    class ListRepoHolder(private val binding: ListRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userRepos: UserRepositoryResponseItem) {
            with(binding) {
                tvProject.text = userRepos.name
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRepoHolder {
        val listRepoItem = ListRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListRepoHolder(listRepoItem)
    }

    override fun onBindViewHolder(holder: ListRepoHolder, position: Int) {
        val reposItem = userReposList[position]
        holder.bind(reposItem)
    }

    override fun getItemCount(): Int = userReposList.size
}