package com.example.kiddrawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)

        drawingView?.setSizeForBrush(20.toFloat())

        val linearLayoutPaintColor = findViewById<LinearLayout>(R.id.ll_paint_color)

        mImageButtonCurrentPaint = linearLayoutPaintColor[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.pallet_normal)
        )


        val ib_brush: ImageButton = findViewById(R.id.ib_brush)
        ib_brush.setOnClickListener {
            showBrushSizeChooserDialog()
        }


    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dailog_brush_size)
        brushDialog.setTitle("Brush Size:")
        val smallbtn = brushDialog.findViewById<ImageButton>(R.id.ib_small_brush)
        val mediumbtn = brushDialog.findViewById<ImageButton>(R.id.ib_medium_brush)
        val largebtn = brushDialog.findViewById<ImageButton>(R.id.ib_large_brush)
        smallbtn.setOnClickListener {
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()

        }
        mediumbtn.setOnClickListener {
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()

        }
        largebtn.setOnClickListener {
            drawingView?.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()

        }
        brushDialog.show()

    }

    fun paintClicked(view:View){
        if (view !==mImageButtonCurrentPaint){
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint = view
        }
    }

}