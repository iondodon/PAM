package com.example.lab3.ui.listAllPosts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab3.R
import com.example.lab3.worker.LoadRecyclerView


class ListAllPostsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_list_all_posts, container, false)

        LoadRecyclerView().execute(root)

        return root
    }
}