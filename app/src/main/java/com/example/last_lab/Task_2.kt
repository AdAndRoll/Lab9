package com.example.last_lab

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class Task_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_2)

        val spriteImage = findViewById<ImageView>(R.id.spriteImage)
        val spriteAnimation = spriteImage.drawable as AnimationDrawable
        spriteAnimation.start()
    }
}
