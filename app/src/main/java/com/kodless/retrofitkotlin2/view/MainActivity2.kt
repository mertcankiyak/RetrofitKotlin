package com.kodless.retrofitkotlin2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodless.retrofitkotlin2.R
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = intent
        tvGoster.text = intent.getStringExtra("veri")
    }
}