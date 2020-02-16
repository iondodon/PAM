package com.utm.lab1

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

const val EXTRA_MESSAGE = "com.utm.lab1.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setMessage(view: View) {
        val message: TextView = findViewById(R.id.messageId)
        val input: TextInputEditText = findViewById(R.id.textInput)
        message.text = input.text
    }

    fun openShowMessageActivity(View: View) {
        val editText: TextInputEditText = findViewById(R.id.textInput)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    fun openPostsActivity(View: View) {
        startActivity(Intent(this, ListPosts::class.java))
    }
}
