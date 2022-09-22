package com.example.myprofiles.details.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myprofiles.User
import com.example.myprofiles.details.followers.FollowersViewModel
import com.example.myprofiles.model.UserDetailsResponse
import com.example.myprofiles.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    companion object {
        const val TAG = "FollowingViewModel"
    }

    private val _listFollowing = MutableLiveData<List<UserDetailsResponse>>()
    val listFollowing: LiveData<List<UserDetailsResponse>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFollowing(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username)
        client.enqueue(object : Callback<List<UserDetailsResponse>>{
            override fun onResponse(call: Call<List<UserDetailsResponse>>, response: Response<List<UserDetailsResponse>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                } else {
                    Log.e(TAG, "onFailure onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserDetailsResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure onFailure: ${t.message.toString()}")
            }

        })
    }
}