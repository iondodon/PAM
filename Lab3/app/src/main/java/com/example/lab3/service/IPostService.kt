package com.example.lab3.service

import com.example.lab3.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface IPostService {
    @GET("posts")
    fun allPosts(): Call<List<Post>>
}