package com.example.last_lab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.last_lab.com.example.last_lab.Task_4
import com.example.last_lab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTask1.setOnClickListener {
            startActivity(Intent(this, Task_1::class.java))
        }

        binding.btnTask2.setOnClickListener {
            startActivity(Intent(this, Task_2::class.java))
        }

        binding.btnTask3.setOnClickListener {
            startActivity(Intent(this, Task_3::class.java))
        }
        // Кнопка для перехода на Task_4
        val buttonTask4 = findViewById<Button>(R.id.btnTask4)
        buttonTask4.setOnClickListener {
            val intent = Intent(this, Task_4::class.java)
            startActivity(intent)
        }
        binding.btnTask5.setOnClickListener {
            startActivity(Intent(this, Task_5::class.java))
        }
        binding.btnTask6.setOnClickListener {
            startActivity(Intent(this, Task_6::class.java))
        }
    }
}
