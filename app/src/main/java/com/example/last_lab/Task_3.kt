package com.example.last_lab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class Task_3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ParticleView(this))
    }
}

class ParticleView(context: Context) : View(context) {

    private val particles = mutableListOf<Particle>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val handler = Handler(Looper.getMainLooper())

    init {
        // Обновление экрана каждые 16 мс (~60 FPS)
        handler.post(object : Runnable {
            override fun run() {
                updateParticles()
                invalidate()
                handler.postDelayed(this, 16L)
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Рисуем частицы
        for (particle in particles) {
            paint.color = particle.color
            canvas.drawCircle(particle.x, particle.y, particle.size, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            // Создаём новые частицы в месте нажатия
            generateParticles(event.x, event.y)
            return true
        }
        return super.onTouchEvent(event)
    }

    private fun generateParticles(x: Float, y: Float) {
        for (i in 0..50) { // 50 частиц
            particles.add(
                Particle(
                    x = x,
                    y = y,
                    dx = Random.nextFloat() * 4f - 2f, // Случайная скорость по X
                    dy = Random.nextFloat() * 4f - 2f, // Случайная скорость по Y
                    size = Random.nextFloat() * 15f + 5f, // Случайный размер
                    color = Color.rgb(
                        Random.nextInt(256),
                        Random.nextInt(256),
                        Random.nextInt(256)
                    ),
                    lifetime = Random.nextInt(30, 100) // Время жизни частицы
                )
            )
        }
    }

    private fun updateParticles() {
        val iterator = particles.iterator()
        while (iterator.hasNext()) {
            val particle = iterator.next()
            particle.x += particle.dx
            particle.y += particle.dy
            particle.lifetime--

            // Удаляем частицу, если её время жизни закончилось
            if (particle.lifetime <= 0) {
                iterator.remove()
            }
        }
    }
}

data class Particle(
    var x: Float,
    var y: Float,
    var dx: Float,
    var dy: Float,
    var size: Float,
    var color: Int,
    var lifetime: Int
)
