package com.utm.lab1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.utm.lab1.R
import com.utm.lab1.model.Post

class PostCardAdapter(private val postList: ArrayList<Post>):
    RecyclerView.Adapter<PostCardAdapter.ViewHolder>() {

    private var removedPosition = 0
    private lateinit var removedItem: Post

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return ViewHolder(view)
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        removedPosition = viewHolder.adapterPosition
        removedItem = postList[viewHolder.adapterPosition]

        postList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar.make(viewHolder.itemView, "$removedItem deleted.", Snackbar.LENGTH_LONG).setAction("UNDO") {
            postList.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)
        }.show()
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = postList[position]
        holder.textViewTitle.text = post.title
        holder.textViewPostBody.text = post.body
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById(R.id.card_post_title) as TextView
        val textViewPostBody = itemView.findViewById(R.id.card_post_body) as TextView

    }
}