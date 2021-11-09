package com.nsolution.nfooddriver.Ui.Authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Base.BaseFragment
import com.nsolution.nfooddriver.Ui.HomeScreen.HomeScreen
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignInFragment : BaseFragment() {

  private val forgotPasswordFragment = ForgotPasswordFragment()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_sign_in, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initialView()
  }

  private fun initialView() {
    nextButton.setOnClickListener {
      signInAction()
    }

    forgotPassword.setOnClickListener {
      addFragmentToStack(forgotPasswordFragment)
    }

    toolbar.setOnClickListener {
      activity?.onBackPressed()
    }
  }

  private fun navigateToHomeScreen(){
    val intent = Intent(context, HomeScreen::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
    requireActivity().finish()
  }

  private fun signInAction(){
    val email = enterEmail.text.toString()
    val password = enterPassword.text.toString()

    navigateToHomeScreen()
  }
}
