package com.example.lab3.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PostService {
    private var service: IPostService

    fun getService(): IPostService {
        return service
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(IPostService::class.java)
    }
}