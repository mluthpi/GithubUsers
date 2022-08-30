package com.example.myprofiles.network

import com.example.myprofiles.ResponseItem
import com.example.myprofiles.UserResponse
import com.example.myprofiles.model.UserDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    fun getUser() : Call<UserResponse>

    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String) : Call<UserDetailsResponse>


}