package com.example.androidnotepadclient

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ViewActivity : AppCompatActivity() {
    private val AUTHORITY = "com.example.androidnotepad.user_notepad"
    val CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/notes")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        fetch()
    }

    fun fetch() {
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
            Toast.makeText(this@ViewActivity, "" + list.size, Toast.LENGTH_SHORT).show()
        }
    }
}