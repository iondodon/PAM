package com.utm.lab1.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utm.lab1.R
import com.utm.lab1.adapter.PostCardAdapter
import com.utm.lab1.model.Post
import java.net.HttpURLConnection
import java.net.URL

class ListPostsActivity : AppCompatActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @RequiresApi(Build.VERSION_CODES.N)
    fun getPosts(): ArrayList<Post> {
        val posts: ArrayList<Post> = ArrayList()

        val url = URL("https://jsonplaceholder.typicode.com/posts")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    Log.d("MY", line)
                }
            }
        }

        return posts
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_posts)

        val posts: ArrayList<Post> = this.getPosts()

        viewManager = LinearLayoutManager(this)
        viewAdapter = PostCardAdapter(posts)

        postsRecyclerView = findViewById<RecyclerView>(R.id.postsRecyclerView)
        postsRecyclerView.layoutManager = viewManager
        postsRecyclerView.adapter = viewAdapter
    }
}
