package com.example.myprofiles.details.followers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myprofiles.databinding.ActivityFollowersBinding
import com.example.myprofiles.model.UserDetailsResponse


class FollowersActivity : AppCompatActivity() {

    companion object {
        const val FOLLOWERS = "FOLLOWERS"
    }

    private var _binding : ActivityFollowersBinding? = null
    private val binding get() = _binding!!

    private lateinit var followersAdapter : FollowersAdapter
    private lateinit var followersViewModel : FollowersViewModel

    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFollowersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Followers"

        username = intent.getStringExtra(FOLLOWERS) ?: ""

        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)
        followersViewModel.getFollowers(username)
        observeViewModel()



    }

    private fun observeViewModel() {
        followersViewModel.listFollowers.observe(this, {listFollowers ->
            showFollowers(listFollowers)
        })

        followersViewModel.isLoading.observe(this, {isLoading ->
            showLoading(isLoading)
        })
    }


    private fun showFollowers(followers: UserDetailsResponse) {
        followersAdapter = FollowersAdapter()
        followersAdapter.addItems(followers)
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(
                this@FollowersActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = followersAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}