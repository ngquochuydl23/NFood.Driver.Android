package com.nsolution.nfooddriver.Ui.Authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : BaseFragment() {

  private val signInFragment = SignInFragment()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_welcome, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initialView()
  }

  private fun initialView() {
    signInButton.setOnClickListener {
      addFragmentToStack(signInFragment)
    }
    signUpButton.setOnClickListener {
      //addFragmentToStack(signUpDriverFragment)
    }
  }
}
