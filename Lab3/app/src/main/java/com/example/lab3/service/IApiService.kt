package com.example.lab3.service

import com.example.lab3.model.Photo
import com.example.lab3.model.Post
import retrofit2.Call
import retrofit2.http.*


interface IApiService {
    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @GET("posts")
    fun posts(): Call<List<Post>>

    @GET("photos")
    fun photos(): Call<List<Photo>>

    /**
     * Important: the resource will not be really created on the server but it will be faked as if.
     * In other words, if you try to access a post using 101 as an id, you'll get a 404 error.
     */
    @POST("posts")
    @Headers("Content-type: application/json; charset=UTF-8")
    fun createPost(@Body post: Post?): Call<Post?>?

    @Headers("Content-type: application/json; charset=UTF-8")
    fun updatePost(@Body updatedPost: Post?): Call<Post?>?
}