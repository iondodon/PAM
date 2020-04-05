package com.example.lab3.worker

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.View
import android.widget.TextView
import com.example.lab3.R
import com.example.lab3.model.Post
import com.example.lab3.service.ApiService
import retrofit2.Call
import retrofit2.Response

class UpdatePost(
    private val callerView: View,
    private val post: Post
): AsyncTask<Void, Void, Post?>() {

    override fun doInBackground(vararg params: Void?): Post? {
        return try {
            val apiService = ApiService()
            val getCall: Call<Post?>? = apiService.getService().updatePost(post)
            val returnedPost: Response<Post?>? = getCall?.execute()
            returnedPost?.body()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onPreExecute() {
        super.onPreExecute()
        val status = this.callerView.findViewById<TextView>(R.id.update_post_result)
        status.text = "Updating..."
    }

    override fun onPostExecute(result: Post?) {
        super.onPostExecute(result)
        val status = this.callerView.findViewById<TextView>(R.id.update_post_result)
        status.text = result.toString()
    }
}