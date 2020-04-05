package com.example.lab3.ui.updatePost

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lab3.R
import com.example.lab3.model.Post
import com.example.lab3.worker.GetRandomPost
import com.example.lab3.worker.UpdatePost


class UpdatePostFragment : Fragment() {

    private lateinit var root: View
    private lateinit var job: AsyncTask<Void, Void, Post?>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.root = inflater.inflate(R.layout.fragment_update_post, container, false)

        val updatePostButton: Button = this.root.findViewById(R.id.update_post_button)
        updatePostButton.setOnClickListener {
            this.updatePost()
        }

        this.job = GetRandomPost(this.root).execute()

        return this.root
    }

    private fun updatePost() {
        val post: Post? = this.job.get()
        if (post != null) {
            UpdatePost(this.root, post).execute()
        }
    }
}