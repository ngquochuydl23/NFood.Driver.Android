package com.nsolution.nfooddriver.Ui.Authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forgot_password.*


class ForgotPasswordFragment : BaseFragment() {

  private val confirmPasswordFragment = VerificationFragment()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_forgot_password, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    toolbar.setOnClickListener {
      activity?.onBackPressed()
    }

    nextButton.setOnClickListener {
      sendEmailToServer()
    }
  }

  private fun sendEmailToServer(){
    val email = enterEmail.text.toString()

    addFragmentToStack(confirmPasswordFragment)
  }
}
