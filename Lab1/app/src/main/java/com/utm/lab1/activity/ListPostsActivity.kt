package com.utm.lab1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.utm.lab1.R
import com.utm.lab1.adapter.PostCardAdapter
import com.utm.lab1.model.Post

class ListPostsActivity : AppCompatActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private fun getPosts() {

        val httpAsync = "https://jsonplaceholder.typicode.com/posts"
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        val data = result.get()
                        val gson = Gson()
                        val sType = object : TypeToken<List<Post>>() { }.type
                        val otherList = gson.fromJson<List<Post>>(data, sType)
                        val posts: ArrayList<Post>  = otherList as ArrayList<Post>
                        
                        showRecycleView(posts)
                    }
                }
            }

        httpAsync.join()
    }

    private fun showRecycleView(posts: ArrayList<Post>) {
        viewAdapter = PostCardAdapter(posts)
        postsRecyclerView = findViewById(R.id.postsRecyclerView)
        postsRecyclerView.layoutManager = viewManager
        postsRecyclerView.adapter = viewAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_posts)
        viewManager = LinearLayoutManager(this)

        this.getPosts()
    }

}
