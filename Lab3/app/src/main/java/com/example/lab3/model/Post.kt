package com.example.lab3.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
)