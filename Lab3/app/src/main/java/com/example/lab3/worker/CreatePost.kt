package com.example.lab3.worker

import android.os.AsyncTask
import android.view.View
import android.widget.TextView
import com.example.lab3.R
import com.example.lab3.model.Post
import com.example.lab3.service.ApiService
import java.lang.Exception


class CreatePost(
    private val callerView: View,
    private val title: String,
    private val body: String
): AsyncTask<Void, Void, Post?>() {

    override fun doInBackground(vararg params: Void?): Post? {
        val newPost = Post(title, body, 0)

        return try {
            val apiService = ApiService()
            val postCall = apiService.getService().createPost(newPost)
            val returnedPost = postCall?.execute()
            returnedPost?.body()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    override fun onPostExecute(result: Post?) {
        super.onPostExecute(result)

        this.callerView.findViewById<TextView>(R.id.create_post_result).text = result.toString()
    }
}