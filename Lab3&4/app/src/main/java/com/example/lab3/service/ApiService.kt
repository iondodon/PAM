package com.example.lab3.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiService {
    private var service: IApiService

    fun getService(): IApiService {
        return service
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(IApiService::class.java)
    }
}