package com.example.lab3.worker

import android.os.AsyncTask
import com.example.lab3.model.Post
import com.example.lab3.service.ApiService
import retrofit2.Call
import retrofit2.Response

class GetRandomPost: AsyncTask<Void, Void, Post?>() {

    override fun doInBackground(vararg params: Void?): Post? {
        val id: Int = (1..3).shuffled().first()

        return try {
            val apiService = ApiService()
            val getCall: Call<Post> = apiService.getService().getPost(id)
            val returnedPost: Response<Post?>? = getCall.execute()
            returnedPost?.body()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
    }
}