package com.nsolution.nfooddriver.Ui.Authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_verification.*


class VerificationFragment : BaseFragment() {

  private val enterNewPasswordFragment = EnterNewPasswordFragment()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_verification, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initialView()
  }

  private fun initialView(){
    nextButton.setOnClickListener {
      sendOTPtoServer()
    }

    toolbar.setNavigationOnClickListener {
      activity?.onBackPressed()
    }
  }

  private fun sendOTPtoServer(){
    val otpCode = enterOtpCode.text.toString()

    addFragmentToStack(enterNewPasswordFragment)
  }
}
