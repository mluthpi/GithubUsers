package com.example.myprofiles.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myprofiles.model.UserDetailsResponse
import com.example.myprofiles.model.UserRepositoryResponseItem
import com.example.myprofiles.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    companion object {
        private const val TAG = "DetailViewModel"
    }


    private val _detailsUser = MutableLiveData<UserDetailsResponse>()
    val detailsUser: LiveData<UserDetailsResponse> = _detailsUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _detailRepos = MutableLiveData<List<UserRepositoryResponseItem>>()
    val detailRepos: LiveData<List<UserRepositoryResponseItem>> = _detailRepos

    fun getDetailsUser(username: String) {
        val client = ApiConfig.getApiService().getUserDetails(username)
        client.enqueue(object : Callback<UserDetailsResponse> {
            override fun onResponse(
                call: Call<UserDetailsResponse>,
                response: Response<UserDetailsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailsUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure onResponse: ${response.message()}" )
                }
            }

            override fun onFailure(call: Call<UserDetailsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure onFailure: ${t.message.toString()}" )
            }
        })
    }

    fun getUserRepos(username: String) {
        val client = ApiConfig.getApiService().getReposDetails(username)
        client.enqueue(object : Callback<List<UserRepositoryResponseItem>> {
            override fun onResponse(
                call: Call<List<UserRepositoryResponseItem>>,
                response: Response<List<UserRepositoryResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailRepos.value = response.body()
                } else {
                    Log.e(TAG, "onFailure onResponse: ${response.message()}" )
                }
            }

            override fun onFailure(call: Call<List<UserRepositoryResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure onFailure: ${t.message.toString()}" )
            }
        })
    }

}