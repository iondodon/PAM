package com.utm.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

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
}
