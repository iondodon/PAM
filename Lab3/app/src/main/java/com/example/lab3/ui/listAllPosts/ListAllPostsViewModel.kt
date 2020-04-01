package com.example.lab3.ui.listAllPosts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListAllPostsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "List all posts"
    }

    val text: LiveData<String> = _text
}