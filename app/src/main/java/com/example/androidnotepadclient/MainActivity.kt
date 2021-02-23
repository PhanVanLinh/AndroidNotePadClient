package com.example.androidnotepadclient

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val AUTHORITY = "com.example.androidnotepad.user_notepad"
    private val CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/notes")
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.text_result)

        fetch()
    }

    private fun fetch() {
        val cursor = contentResolver.query(
            CONTENT_URI,
            null, null, null, null
        )

        cursor?.apply {
            val idIndex: Int = getColumnIndex("id")
            val titleIndex: Int = getColumnIndex("title")
            val list = mutableListOf<Note>()

            while (moveToNext()) {
                val id = getInt(idIndex)
                val title = getString(titleIndex)
                val description = getString(2)
                val viewCount = getInt(3)
                list.add(Note(id, title, description, viewCount))
            }

            Toast.makeText(this@MainActivity, "" + list.size, Toast.LENGTH_SHORT).show()

            list.forEach {
                tvResult.text = ("\n" + it.title) + tvResult.text
            }
        }
    }
}