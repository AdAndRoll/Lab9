package com.example.last_lab

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Task_5 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false
    private var currentTrackIndex = 0  // Индекс текущего трека
    private val tracks = arrayOf(R.raw.track1, R.raw.track2, R.raw.track3) // Список треков

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_5)

        // Подготовка первого трека
        prepareMediaPlayer()

        val playButton: Button = findViewById(R.id.playButton)
        val nextTrackButton: Button = findViewById(R.id.nextButton)
        val previousTrackButton: Button = findViewById(R.id.previousButton)

        // Обработчик нажатия на кнопку "Play"
        playButton.setOnClickListener {
            if (isPlaying) {
                stopMusic(playButton)
            } else {
                playMusic(playButton)
            }
        }

        // Обработчик нажатия на кнопку "Next Track"
        nextTrackButton.setOnClickListener {
            switchTrackNext()
        }

        // Обработчик нажатия на кнопку "Previous Track"
        previousTrackButton.setOnClickListener {
            switchTrackPrevious()
        }
    }

    // Метод для подготовки MediaPlayer
    private fun prepareMediaPlayer() {
        try {
            // Загружаем текущий трек из массива
            mediaPlayer = MediaPlayer.create(this, tracks[currentTrackIndex])
            mediaPlayer.setOnCompletionListener {
                // Когда трек заканчивается, обновляем кнопку и переключаем на следующий трек
                val playButton: Button = findViewById(R.id.playButton)
                playButton.text = "Play"
                isPlaying = false
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to prepare media player: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Метод для воспроизведения музыки
    private fun playMusic(playButton: Button) {
        if (!mediaPlayer.isPlaying) {
            try {
                mediaPlayer.start()  // Запускаем музыку
                playButton.text = "Stop"  // Меняем кнопку на Stop
                isPlaying = true
            } catch (e: Exception) {
                Toast.makeText(this, "Error playing music: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Метод для остановки музыки
    private fun stopMusic(playButton: Button) {
        if (mediaPlayer.isPlaying) {
            try {
                mediaPlayer.stop()  // Останавливаем музыку
                mediaPlayer.prepare()  // Подготавливаем для следующего воспроизведения
                playButton.text = "Play"  // Меняем кнопку на Play
                isPlaying = false
            } catch (e: Exception) {
                Toast.makeText(this, "Error stopping music: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Метод для переключения на следующий трек
    private fun switchTrackNext() {
        // Останавливаем текущий трек, если он играет
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }

        // Увеличиваем индекс трека (если мы достигли конца списка, начинаем сначала)
        currentTrackIndex = (currentTrackIndex + 1) % tracks.size

        // Подготавливаем следующий трек
        prepareMediaPlayer()

        // Включаем новый трек
        val playButton: Button = findViewById(R.id.playButton)
        playButton.text = "Play"
        isPlaying = false
    }

    // Метод для переключения на предыдущий трек
    private fun switchTrackPrevious() {
        // Останавливаем текущий трек, если он играет
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }

        // Уменьшаем индекс трека (если мы на первом треке, переходим к последнему)
        currentTrackIndex = if (currentTrackIndex - 1 < 0) {
            tracks.size - 1 // Если первый трек, переходим к последнему
        } else {
            currentTrackIndex - 1
        }

        // Подготавливаем предыдущий трек
        prepareMediaPlayer()

        // Включаем новый трек
        val playButton: Button = findViewById(R.id.playButton)
        playButton.text = "Play"
        isPlaying = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()  // Освобождаем ресурсы
        }
    }
}
