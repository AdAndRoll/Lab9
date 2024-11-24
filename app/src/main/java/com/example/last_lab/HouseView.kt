package com.example.last_lab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class HouseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.LTGRAY
        canvas.drawRect(200f, 400f, 600f, 800f, paint)

        paint.color = Color.RED
        val roofPath = android.graphics.Path().apply {
            moveTo(200f, 400f)
            lineTo(600f, 400f)
            lineTo(400f, 200f)
            close()
        }
        canvas.drawPath(roofPath, paint)

        paint.color = Color.BLUE
        canvas.drawRect(250f, 450f, 350f, 550f, paint)
        canvas.drawRect(450f, 450f, 550f, 550f, paint)

        paint.color = Color.DKGRAY
        canvas.drawRect(350f, 600f, 450f, 800f, paint)
    }
}
