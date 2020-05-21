package com.example.lab3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.model.Photo
import com.example.lab3.worker.GetImageFromUrl
import com.google.android.material.snackbar.Snackbar

class PhotoCardAdapter(private val photoList: ArrayList<Photo>):
    RecyclerView.Adapter<PhotoCardAdapter.ViewHolder>() {

    private var removedPosition = 0
    private lateinit var removedItem: Photo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_card_layout, parent, false)
        return ViewHolder(view)
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        removedPosition = viewHolder.adapterPosition
        removedItem = photoList[viewHolder.adapterPosition]

        photoList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar.make(viewHolder.itemView, "$removedItem deleted.", Snackbar.LENGTH_LONG).setAction("UNDO") {
            photoList.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)
        }.show()
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo: Photo = photoList[position]


        holder.textViewPhotoTitle.text = photo.title
        holder.textViewPhotoUrl.text = photo.url
        GetImageFromUrl(holder.imageView).execute(null)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewPhotoTitle = itemView.findViewById(R.id.card_photo_title) as TextView
        val textViewPhotoUrl = itemView.findViewById(R.id.card_photo_url) as TextView
        val imageView = itemView.findViewById(R.id.card_photo) as ImageView
    }
}