package com.nsolution.nfooddriver.Ui.Trip


import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_take_photo_order.*
import kotlinx.android.synthetic.main.activity_take_photo_order.header


class TakePhotoOrder : BaseActivity() {

  private val DELIVERY_FOOD_STEP = "DELIVERY_FOOD_STEP"
  private val REQUEST_IMAGE_CAPTURE = 1
  private val PERMISSION_CODE = 101
  private var photoURI: Uri? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_take_photo_order)

    initialView()
  }

  private fun initialView() {
    getBackActionBar(header, getString(R.string.take_photo_order))

    cameraView.setOnClickListener {
      checkPermission()
    }

    nextButton.setOnClickListener {
      backToTripActivity()
      showToast("Sent image to NFood")
    }
  }

  private fun backToTripActivity() {
    finish()
    ConfirmTakeOrder.activity.finish()
    TakeOrder.activity.finish()
    Trip.restaurantInformationLayout.visibility = View.GONE
    Trip.currentStep = DELIVERY_FOOD_STEP
  }

  private fun showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      cameraView.setImageURI(photoURI)
    }
  }

  private fun openCamera() {
    val contentValue = ContentValues()
    contentValue.put(MediaStore.Images.Media.TITLE, "New Picture")
    contentValue.put(MediaStore.Images.Media.DESCRIPTION, "From the camera")

    photoURI = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue)

    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
  }

  private fun checkPermission() {
    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
      && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
    ) {
      val permissions =
        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
      requestPermissions(permissions, PERMISSION_CODE)
    } else {
      openCamera()
    }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == PERMISSION_CODE && grantResults.size > 0) {
      openCamera()
    }
  }
}
