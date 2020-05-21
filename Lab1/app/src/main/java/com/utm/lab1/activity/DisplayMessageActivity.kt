package com.utm.lab1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.utm.lab1.R


class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val incomingMessage = intent.getStringExtra(EXTRA_MESSAGE)

        findViewById<TextView>(R.id.displayMessage_messageId).apply {
            text = incomingMessage
        }
    }

}
