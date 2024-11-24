package com.example.last_lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Task_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(HouseView(this))
    }
}
