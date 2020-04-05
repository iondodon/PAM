package com.example.lab3.worker

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.View
import android.widget.TextView
import com.example.lab3.R
import com.example.lab3.model.Post
import com.example.lab3.service.ApiService
import retrofit2.Response
import java.lang.Exception


class CreatePost(
    private val callerView: View,
    private val title: String,
    private val body: String
): AsyncTask<Void, Void, Response<Post?>?>() {

    override fun doInBackground(vararg params: Void?): Response<Post?>? {
        val id: Int = (101..200).shuffled().first()
        val userId: Int = (101..200).shuffled().first()
        val newPost = Post(id, title, body, userId)

        return try {
            val apiService = ApiService()
            val postCall = apiService.getService().createPost(newPost)
            val returnedPost = postCall?.execute()
            returnedPost
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onPreExecute() {
        super.onPreExecute()
        this.callerView.findViewById<TextView>(R.id.create_post_result).text = "Waiting for response..."
    }

    override fun onPostExecute(result: Response<Post?>?) {
        super.onPostExecute(result)

        this.callerView.findViewById<TextView>(R.id.create_post_result).text = result.toString()
    }
}