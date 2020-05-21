package com.example.lab3.ui.updatePost

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
import com.example.lab3.worker.GetRandomPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response


class UpdatePostFragment : Fragment() {
    private lateinit var root: View
    private var randomPost: Post? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.root = inflater.inflate(R.layout.fragment_update_post, container, false)

        val updatePostButton: Button = this.root.findViewById(R.id.update_post_button)
        updatePostButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val title = root.findViewById<EditText>(R.id.update_post_title_input).text.toString()
                val body = root.findViewById<EditText>(R.id.update_post_body_input).text.toString()
                randomPost?.title = title
                randomPost?.body = body
                showMessage("Waiting for response...")
                val response: Response<Post?>? = update()
                showMessage("Response: " + response.toString() + "\nPost: " + response?.body().toString())
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            showMessage("Fetching a random post...")
            randomPost = GetRandomPost().execute().get()
            fillFields()
        }

        return this.root
    }

    private suspend fun fillFields() = withContext(Dispatchers.Main) {
        val title = root.findViewById<EditText>(R.id.update_post_title_input)
        val body = root.findViewById<EditText>(R.id.update_post_body_input)

        if (randomPost != null) {
            title.setText(randomPost!!.title)
            body.setText(randomPost!!.body)
            setUpdateButtonVisible()
            showMessage("Random post fetched.")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpdateButtonVisible() {
        val button: Button = root.findViewById(R.id.update_post_button) as Button
        button.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private suspend fun showMessage(message: String) = withContext(Dispatchers.Main) {
        val messageTextView = root.findViewById<TextView>(R.id.update_post_result)
        messageTextView.text = message
    }

    private fun update(): Response<Post?>? {
        return try {
            val apiService = ApiService()
            val updateCall: Call<Post?>? = apiService.getService().updatePost(randomPost, randomPost!!.id)
            val returnedPost: Response<Post?>? = updateCall?.execute()
            returnedPost
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
    }
}