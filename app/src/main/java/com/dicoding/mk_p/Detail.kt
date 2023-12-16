package com.dicoding.mk_p

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val photo = findViewById<ImageView>(R.id.imageView)
        val name = findViewById<TextView>(R.id.titleTextView)
        val description = findViewById<TextView>(R.id.contentTextView)
        val button = findViewById<Button>(R.id.backButton)

        val intentData = intent
        photo.setImageResource(intentData.getIntExtra("imageView", 0))
        name.text = intentData.getStringExtra("titleTextView")
        description.text = intentData.getStringExtra("contentTextView")

        button.setOnClickListener{
            finish()
        }
    }
}