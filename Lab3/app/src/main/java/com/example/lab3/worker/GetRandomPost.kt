package com.example.lab3.worker

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.lab3.R
import com.example.lab3.model.Post
import com.example.lab3.service.ApiService
import retrofit2.Call
import retrofit2.Response

class GetRandomPost(private val callerView: View): AsyncTask<Void, Void, Post?>() {

    override fun doInBackground(vararg params: Void?): Post? {
        val id: Int = (101..199).shuffled().first()

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

    @SuppressLint("SetTextI18n")
    override fun onPreExecute() {
        super.onPreExecute()
        val status = this.callerView.findViewById<TextView>(R.id.update_post_result)
        status.text = "Getting random post..."
    }

    @SuppressLint("SetTextI18n")
    override fun onPostExecute(result: Post?) {
        super.onPostExecute(result)

        val title = this.callerView.findViewById<EditText>(R.id.update_post_title_input)
        val body = this.callerView.findViewById<EditText>(R.id.update_post_body_input)

        if (result != null) {
            title.setText(result.title)
            body.setText(result.body)

            val button = this.callerView.findViewById<Button>(R.id.update_post_body_input)
            button.visibility = View.VISIBLE
        }

        val status = this.callerView.findViewById<TextView>(R.id.update_post_result)
        status.text = "Post fetched."
    }
}