package com.nsolution.nfooddriver.Ui.Authentication


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Service.DriverService
import com.nsolution.nfooddriver.Ui.Base.BaseActivity

class Authentication : BaseActivity() {

  private val welcomeFragment = WelcomeFragment()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_authentication)
    addFragmentToStack(welcomeFragment)
  }

  fun addFragmentToStack(fragment: Fragment) {
    supportFragmentManager.commit {
      add(R.id.fragmentContainer, fragment)
      show(fragment)
    }
  }
}
