package com.example.myprofiles.details.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myprofiles.User
import com.example.myprofiles.details.followers.FollowersViewModel
import com.example.myprofiles.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    companion object {
        const val TAG = "FollowingViewModel"
    }

    private val _listFollowing = MutableLiveData<List<User>>()
    val listFollowing: LiveData<List<User>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFollowing(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username)
        client.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body() ?: emptyList()
                } else {
                    _listFollowing.value = emptyList()
                    Log.e(FollowersViewModel.TAG, "onFailure onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _isLoading.value = false
                Log.e(FollowersViewModel.TAG, "onFailure onFailure: ${t.message.toString()}")
            }

        })
    }
}