package com.example.myprofiles.details.following

import com.example.myprofiles.databinding.ActivityFollowingBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myprofiles.User
import com.example.myprofiles.details.followers.FollowersActivity
import com.example.myprofiles.model.UserDetailsResponse

class FollowingActivity : AppCompatActivity() {

    companion object {
        const val FOLLOWING = "FOLLOWING"
    }

    private var _binding : ActivityFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var followingAdapter : FollowingAdapter
    private lateinit var followingViewModel : FollowingViewModel
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFollowingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Following"

        username = intent.getStringExtra(FOLLOWING) ?: ""
        Log.d("TEST_DEBUG", "onCreate: $username")

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)
        followingViewModel.getFollowing(username)
        observeViewModel()



    }

    private fun observeViewModel(){
        followingViewModel.listFollowing.observe(this, {listFollowing ->
            showFollowing(listFollowing)
        })

        followingViewModel.isLoading.observe(this, {isLoading ->
            showLoading(isLoading)
        })
    }


    private fun showFollowing(followers: List<UserDetailsResponse>) {
        followingAdapter = FollowingAdapter()
        followingAdapter.addItems(followers)
        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(
                this@FollowingActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = followingAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}