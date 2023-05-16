package com.android.example.androidarch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // view
        val button = findViewById<Button>(R.id.btn_click)
        // model
        val counter = ModelCountNumber.getNewCount()
        // view model
        val viewModel = CountViewModel(counter)

        // bind
        counter.observe(object : DataObserver<Int> {
            override fun onDataUpdated(newData: Int) {
                button.text = "Click $newData times!"
            }
        })
        button.setOnClickListener {
            viewModel.requestNewCount()
        }
    }
}