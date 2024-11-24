package com.example.last_lab.com.example.last_lab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.last_lab.Particle
import kotlin.random.Random

class Task_4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ParticleFountainView(this))
    }
}

class ParticleFountainView(context: Context) : View(context) {

    private val particles = mutableListOf<Particle>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var touchX = 0f
    private var touchY = 0f
    private var isTouching = false

    init {
        // Обновление экрана каждые 16 мс (~60 FPS)
        postDelayed(object : Runnable {
            override fun run() {
                updateParticles()
                invalidate()
                postDelayed(this, 16L)
            }
        }, 16L)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Рисуем все частицы
        for (particle in particles) {
            paint.color = particle.color
            canvas.drawCircle(particle.x, particle.y, particle.size, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        touchX = event.x
        touchY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Начать выброс частиц при касании
                isTouching = true
                generateParticles()
            }
            MotionEvent.ACTION_MOVE -> {
                // Генерировать частицы при движении пальца
                if (isTouching) {
                    generateParticles()
                }
            }
            MotionEvent.ACTION_UP -> {
                // Остановить выброс частиц при отпускании
                isTouching = false
            }
        }
        return true
    }

    private fun generateParticles() {
        // Генерация частиц на основе положения пальца
        for (i in 0 until 5) {  // Генерация 5 частиц за один момент времени
            val angle = Random.nextFloat() * 360f  // случайный угол
            val speed = Random.nextFloat() * 10f + 5f  // случайная скорость вверх
            particles.add(
                Particle(
                    x = touchX,
                    y = touchY,
                    dx = speed * Math.cos(Math.toRadians(angle.toDouble())).toFloat(),
                    dy = -speed,  // Двигаем частицы вверх (отрицательное значение по Y)
                    size = Random.nextFloat() * 10f + 5f,
                    color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                    lifetime = Random.nextInt(50, 100)
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
            particle.dy += 0.1f // Симуляция гравитации (замедление вверх)

            // Удаляем частицы, когда они выходят за верхнюю границу экрана
            if (particle.y < 0 || particle.lifetime <= 0) {
                iterator.remove()
            }
            particle.lifetime--
        }
    }
}


