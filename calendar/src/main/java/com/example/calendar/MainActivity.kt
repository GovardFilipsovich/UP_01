package com.example.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.time.LocalDate
import java.util.Date

class MainActivity : AppCompatActivity() {
    private var date = LocalDate.of(2003,9, 18)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}