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
    fun getReposDetails(@Path("username") username: String) : Call<List<UserRepositoryResponseItem>>

    @GET("users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String) : Call<List<UserDetailsResponse>>

    @GET("users/{username}/following")
    fun getUserFollowing(@Path("username") username: String) : Call<List<User>>
}