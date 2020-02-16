package com.utm.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.util.Log

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val incomingMessage = intent.getStringExtra(EXTRA_MESSAGE)

        Log.d("MY", incomingMessage)

        findViewById<TextView>(R.id.displayMessage_messageId).apply {
            text = incomingMessage
        }
    }

}
