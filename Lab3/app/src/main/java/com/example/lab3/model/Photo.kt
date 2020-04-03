package com.example.lab3.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String
)