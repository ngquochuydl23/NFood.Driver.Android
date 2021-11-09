package com.nsolution.nfooddriver.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Camera
import android.view.SurfaceHolder
import android.view.SurfaceView


@SuppressLint("ViewConstructor")
class CameraPreview(context: Context, private var mCamera: Camera?) : SurfaceView(context),
  SurfaceHolder.Callback {

  override fun surfaceCreated(holder: SurfaceHolder) {
    mCamera = Camera.open()
    mCamera?.setPreviewDisplay(holder)
    mCamera?.startPreview()
  }

  override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    mCamera?.startPreview()
  }

  override fun surfaceDestroyed(holder: SurfaceHolder) {
    mCamera?.stopPreview()
    mCamera?.release()
    mCamera = null
  }
}