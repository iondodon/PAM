package com.utm.lab1.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
)