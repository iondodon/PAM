package com.example.lab3.ui.createPost

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab3.R
import com.example.lab3.model.Post
import com.example.lab3.service.ApiService
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception


class CreatePostFragment : Fragment() {
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.root = inflater.inflate(R.layout.fragment_create_post, container, false)

        val postButton: Button = this.root.findViewById(R.id.post_button)

        postButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                createPost()
            }
        }

        return this.root
    }

    @SuppressLint("SetTextI18n")
    private suspend fun createPost() = withContext(Dispatchers.Main) {
        val title: String = root.findViewById<EditText>(R.id.title_input).text.toString()
        val body: String = root.findViewById<EditText>(R.id.body_input).text.toString()

        val id: Int = (101..200).shuffled().first()
        val userId: Int = (101..200).shuffled().first()
        val post = Post(id, title, body, userId)

        root.findViewById<TextView>(R.id.create_post_result).text = "Waiting for response..."
        val returnedPost: Response<Post?>? = postNewPostAsync(post).await()
        root.findViewById<TextView>(R.id.create_post_result).text =
            "Response: " + returnedPost.toString() + "\nPost: " + returnedPost?.body().toString()
    }

    private fun postNewPostAsync(post: Post): Deferred<Response<Post?>?> = CoroutineScope(Dispatchers.IO).async {
        try {
            val apiService = ApiService()
            val postCall = apiService.getService().createPost(post)
            return@async postCall?.execute()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return@async null
        }
    }
}