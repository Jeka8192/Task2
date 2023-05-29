package com.example.myprofilelayouts.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.example.myprofilelayouts.R

class MyButtonView(context: Context, attributes: AttributeSet? = null) : View(context, attributes) {
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.white_grey_light, null)
    //override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        //super.onSizeChanged(width, height, oldWidth, oldHeight)
        //if (::extraBitmap.isInitialized) extraBitmap.recycle()

        //extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        //extraCanvas = Canvas(extraBitmap)
        //extraCanvas.drawColor(backgroundColor)
    //}

    //override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas)
        //canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    //}

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
        }
        return true
    }
}