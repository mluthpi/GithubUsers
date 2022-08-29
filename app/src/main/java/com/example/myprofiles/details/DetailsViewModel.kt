package com.example.myprofiles.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myprofiles.model.UserDetailsResponse
import com.example.myprofiles.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel {

    companion object {
        private const val TAG = "DetailViewModel"
    }


    private val _detailsUser = MutableLiveData<UserDetailsResponse>()
    val detailsUser: LiveData<UserDetailsResponse> = _detailsUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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

}