package com.example.lab3.ui.createPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.lab3.R
import com.example.lab3.worker.CreatePost


class CreatePostFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.root = inflater.inflate(R.layout.fragment_create_post, container, false)

        val postButton: Button = this.root.findViewById(R.id.post_button)

        postButton.setOnClickListener {
            this.post()
        }

        return this.root
    }

    private fun post() {
        val title: String = this.root.findViewById<EditText>(R.id.title_input).text.toString()
        val body: String = this.root.findViewById<EditText>(R.id.body_input).text.toString()
        CreatePost(this.root, title, body).execute()
    }
}