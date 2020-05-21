package com.example.lab3.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("body")
    var body: String,

    @SerializedName("userId")
    val userId: Int
)