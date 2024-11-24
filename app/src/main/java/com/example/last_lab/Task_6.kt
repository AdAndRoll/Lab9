package com.example.last_lab

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class Task_6 : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_6)

        // Инициализация компонентов
        videoView = findViewById(R.id.videoView)
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        stopButton = findViewById(R.id.stopButton)

        // Установка пути к видео (например, видео из ресурсов)
        val videoUri: Uri = Uri.parse("android.resource://$packageName/${R.raw.sample_video}")
        videoView.setVideoURI(videoUri)

        // Обработчик для кнопки Play
        playButton.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()  // Запускаем видео
                Toast.makeText(this, "Video Playing", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик для кнопки Pause
        pauseButton.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()  // Ставим видео на паузу
                Toast.makeText(this, "Video Paused", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик для кнопки Stop
        stopButton.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.stopPlayback()  // Останавливаем воспроизведение видео
                videoView.resume()  // Подготовим VideoView к следующему воспроизведению
                Toast.makeText(this, "Video Stopped", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработка окончания воспроизведения видео
        videoView.setOnCompletionListener {
            Toast.makeText(this, "Video Completed", Toast.LENGTH_SHORT).show()
        }
    }
}
