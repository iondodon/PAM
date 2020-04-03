package com.example.lab3.service

import com.example.lab3.model.Photo
import com.example.lab3.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface IApiService {
    @GET("posts")
    fun posts(): Call<List<Post>>

    @GET("photos")
    fun photos(): Call<List<Photo>>
}