package com.nsolution.nfooddriver.Ui.SplashScreen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Authentication.Authentication
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import com.nsolution.nfooddriver.Ui.HomeScreen.HomeScreen

class SplashScreen : BaseActivity() {

  private val DELAY_MILLIS: Long = 2500
  private val PERMISSION_CODE = 101

  private val permissions = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash_screen)

    initialView()
    checkPermission()
  }

  private fun delaySplashScreen() {
    val handler = Handler()
    handler.postDelayed(delayInMinutes, DELAY_MILLIS)
  }

  private fun initialView() {
    setupWindow()
  }

  private fun setupWindow() {
    getWindow().setFlags(
      WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
      WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
  }

  private val delayInMinutes = Runnable {
    navigateToAuthentication()
  }

  private fun navigateToHomeScreen() {
    val intent = Intent(this, HomeScreen::class.java)
    startActivity(intent)
    finish()
  }

  private fun navigateToAuthentication() {
    val intent = Intent(this, Authentication::class.java)
    startActivity(intent)
    finish()
  }

  private fun checkPermission() {
    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
      && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
      && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
      && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
    ) {
      requestPermissions(permissions, PERMISSION_CODE)
    } else delaySplashScreen()
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == PERMISSION_CODE && grantResults.size > 0) {
      delaySplashScreen()
    }
  }
}
