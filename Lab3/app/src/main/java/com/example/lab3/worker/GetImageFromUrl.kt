package com.example.lab3.worker

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.io.IOException
import java.io.InputStream
import java.net.URL


class GetImageFromUrl(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg params: String?): Bitmap? {

        // it should be -> val stringUrl: String? = params[0], but I found out that the API is broken and
        // it doesn't return any image | FileNotFoundException, so I used another API to get the image
        val stringUrl: String? = "https://picsum.photos/200"

        try {
            val inputStream: InputStream = URL(stringUrl).openStream()
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imageView.setImageBitmap(result)
    }
}