package com.android.example.androidarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.example.androidarch.model.ModelCountNumber
import com.android.example.androidarch.presenter.CountPresenter
import com.android.example.androidarch.view.CountButton

class MainActivity : AppCompatActivity() {
    private lateinit var button: CountButton

    private lateinit var presenter: CountPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn_click)
        presenter = CountPresenter(ModelCountNumber)

        // do bind.
        button.bindPresenter(presenter)
        presenter.bindView(button)
    }
}