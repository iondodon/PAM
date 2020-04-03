package com.example.lab3.worker

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.adapter.PostCardAdapter
import com.example.lab3.model.Post
import com.example.lab3.service.PostService
import retrofit2.Call


class LoadRecyclerView: AsyncTask<View, Int, Boolean>() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))
    private lateinit var deleteIcon: Drawable

    private lateinit var view: View
    private lateinit var posts: ArrayList<Post>

    override fun doInBackground(vararg params: View?): Boolean {
        this.view = params[0]!!

        try {
            val postService = PostService()
            val postsCall: Call<List<Post>> = postService.getService().allPosts()
            val posts = postsCall.execute()
            this.posts = posts.body() as ArrayList<Post>
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }

    // This is called each time you call publishProgress()
    override fun onProgressUpdate(vararg progress: Int?) {

    }

    // This is called when doInBackground() is finished
    override fun onPostExecute(result: Boolean?) {
        showRecycleView(this.posts)
    }

    private fun showRecycleView(posts: ArrayList<Post>) {
        viewAdapter = PostCardAdapter(posts)
        postsRecyclerView = this.view.findViewById(R.id.postsRecyclerView)
        postsRecyclerView.layoutManager = LinearLayoutManager(this.view.context)
        postsRecyclerView.adapter = viewAdapter

        deleteIcon = ContextCompat.getDrawable(this.view.context, R.drawable.ic_delete_24px)!!

        val itemTouchHelperCallback = object:
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                (viewAdapter as PostCardAdapter).removeItem(viewHolder)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                     dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                val itemView = viewHolder.itemView

                val iconMargin = (itemView.height - deleteIcon.intrinsicHeight) / 2

                if(dX > 0) {
                    swipeBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                    deleteIcon.setBounds(
                        itemView.left + iconMargin,
                        itemView.top + iconMargin,
                        itemView.left + iconMargin + deleteIcon.intrinsicWidth,
                        itemView.bottom - iconMargin
                    )
                } else {
                    swipeBackground.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                    deleteIcon.setBounds(
                        itemView.right - iconMargin - deleteIcon.intrinsicWidth,
                        itemView.top + iconMargin,
                        itemView.right - iconMargin,
                        itemView.bottom - iconMargin
                    )
                }

                swipeBackground.draw(c)

                c.save()
                if(dX > 0){
                    c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                } else {
                    c.clipRect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                }
                c.restore()

                deleteIcon.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(postsRecyclerView)
    }
}