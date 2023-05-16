package com.android.example.androidarch.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.example.androidarch.R
import com.android.example.androidarch.model.ModelCountNumber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_click)
        button.setOnClickListener {
            // process user request.
            button.text = "Click ${ModelCountNumber.getNewCount()} times!"
        }
    }
}