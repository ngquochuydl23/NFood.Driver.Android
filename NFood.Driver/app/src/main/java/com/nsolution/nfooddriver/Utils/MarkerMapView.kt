package com.nsolution.nfooddriver.Utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class MarkerMapView(val context: Context) {

  fun getIconMarker(drawableId: Int): Bitmap {
    var drawable = ContextCompat.getDrawable(context, drawableId)
    val bitmap = Bitmap.createBitmap(
      drawable!!.intrinsicWidth,
      drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
  }
}