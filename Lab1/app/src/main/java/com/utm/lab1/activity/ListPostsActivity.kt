package com.utm.lab1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utm.lab1.R
import com.utm.lab1.adapter.PostCardAdapter
import com.utm.lab1.model.Post

class ListPostsActivity : AppCompatActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_posts)

        val posts = ArrayList<Post>()
        posts.add(Post("Title 1", "Body 1"))
        posts.add(Post("Title 2", "Body 2"))
        posts.add(Post("Title 3", "Body 3"))
        posts.add(Post("Title 4", "Body 4"))

        viewManager = LinearLayoutManager(this)
        viewAdapter = PostCardAdapter(posts)

        postsRecyclerView = findViewById<RecyclerView>(R.id.postsRecyclerView)
        postsRecyclerView.layoutManager = viewManager
        postsRecyclerView.adapter = viewAdapter
    }
}
