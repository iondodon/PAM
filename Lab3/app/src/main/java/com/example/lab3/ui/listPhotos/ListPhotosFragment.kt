package com.example.lab3.ui.listPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab3.R
import com.example.lab3.worker.LoadPhotos

class ListPhotosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_list_photos, container, false)

        LoadPhotos().execute(root)

        return root
    }
}