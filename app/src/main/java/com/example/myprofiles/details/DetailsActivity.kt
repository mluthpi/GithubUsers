package com.example.myprofiles.details

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.myprofiles.R
import com.example.myprofiles.databinding.ActivityDetailsBinding
import com.example.myprofiles.model.UserDetailsResponse

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val USERNAME = "USERNAME"
    }

    private lateinit var detailsViewModel: DetailsViewModel

    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailsViewModel.getDetailsUser(username)
        detailsViewModel.detailsUser.observe(this, { userDetails ->
            showUserDetails(userDetails)
        })

        detailsViewModel.isLoading.observe(this, {isLoading ->
            showLoading(isLoading)
        })

    }

    private fun showUserDetails(userDetailResponse: UserDetailsResponse) {
        Glide.with(this)
            .load(userDetailResponse.avatarUrl)
            .into(binding.imgDetail)
        binding.tvDetailName.text = userDetailResponse.name
        binding.tvRepoValue.text = userDetailResponse.publicRepos.toString()
        binding.tvFollowerValue.text = userDetailResponse.followers.toString()
        binding.tvFollowingValue.text = userDetailResponse.following.toString()
        binding.tvCompany.text = userDetailResponse.company ?: ""
        binding.tvLocation.text = userDetailResponse.location ?: ""

        Log.e(TAG, "Observer viewmodel: $userDetailResponse")

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}