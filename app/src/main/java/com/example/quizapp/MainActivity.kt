package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText

const val EXTRA_MESSAGE = "com.example.quiz.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.button)
        val contactButton = findViewById<Button>(R.id.button2)
        startButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editTextTextPersonName2)
            val message = editText.text.toString()
            val intent = Intent(this, NameOfPlayerActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
        contactButton.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, 111)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK){
            val contactURI: Uri = data?.data?: return
            val cols: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val res: Cursor? = contentResolver.query(contactURI, cols, null, null, null)
            if (res?.moveToFirst()!!){
                val userName = findViewById<EditText>(R.id.editTextTextPersonName2)
                userName.setText(res.getString(0))
            }
        }
    }
}