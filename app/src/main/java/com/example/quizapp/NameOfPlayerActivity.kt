package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NameOfPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_of_player)

        // get the intent that started this activity and extract message
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // capture the layout's textView and set the string as its next
        val textView = findViewById<TextView>(R.id.textView3).apply { text = message }
    }
}