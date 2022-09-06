package com.example.myprofiles.network

import com.example.myprofiles.User
import com.example.myprofiles.model.UserDetailsResponse
import com.example.myprofiles.model.UserRepositoryResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    fun getUser() : Call<List<User>>

    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String) : Call<UserDetailsResponse>

    @GET("users/{username}/repos")
    fun getReposDetails() : Call<List<UserRepositoryResponseItem>>
}